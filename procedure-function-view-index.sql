-- Registrar Ordem de Produção
-- Automação do registro de produção sem necessidade de múltiplos comandos INSERT, garantindo consistência.
CREATE PROCEDURE registrar_ordem_producao(IN produto_id INT, IN quantidade INT, IN data_inicio TIMESTAMP, IN status TEXT)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO ordem_producao (produto_id, quantidade, data_inicio, status)
    VALUES (produto_id, quantidade, data_inicio, status);
END;
$$;

-- Calcular Estoque de Produto
-- Auxilia em consultas rápidas para verificar a disponibilidade de um item sem necessidade de múltiplos cálculos externos.

CREATE FUNCTION calcular_estoque(produto_id INT) RETURNS INT
LANGUAGE plpgsql
AS $$
DECLARE
    estoque_atual INT;
BEGIN
    SELECT quantidade INTO estoque_atual FROM estoque WHERE produto_id = produto_id;
    RETURN COALESCE(estoque_atual, 0);
END;
$$;

-- Atualizar Status do Pedido de Compra
-- Facilita a gestão do fluxo de compras, permitindo atualização padronizada do status sem necessidade de manipulação direta da tabela.

CREATE PROCEDURE atualizar_status_pedido(IN pedido_id INT, IN novo_status TEXT)
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE pedido_compra SET status = novo_status WHERE id = pedido_id;
END;
$$;

-- Atualizar Estoque Após Produção
-- Automatiza a movimentação do estoque para evitar inconsistências e necessidade de ajustes manuais.

CREATE TRIGGER atualizar_estoque_producao
AFTER UPDATE ON ordem_producao
FOR EACH ROW
WHEN (NEW.status = 'Finalizado')
EXECUTE FUNCTION atualizar_estoque();

CREATE FUNCTION atualizar_estoque() RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE estoque
    SET quantidade = quantidade + NEW.quantidade
    WHERE produto_id = NEW.produto_id;
    RETURN NEW;
END;
$$;

-- Status das Ordens de Produção
-- Gera uma visão consolidada para acompanhamento da produção.

CREATE VIEW status_ordens_producao AS
SELECT op.id, p.nome AS produto, op.quantidade, op.status
FROM ordem_producao op
JOIN produto p ON op.produto_id = p.id;

-- Materiais Necessários para Produção
-- Facilita a consulta das matérias-primas utilizadas na fabricação.

CREATE VIEW materiais_por_produto AS
SELECT p.nome AS produto, mp.nome AS materia_prima, lm.quantidade
FROM lista_materiais lm
JOIN produto p ON lm.produto_id = p.id
JOIN materia_prima mp ON lm.materia_prima_id = mp.id;

-- Pedidos Pendentes de Compra
-- Permite identificação rápida dos pedidos que ainda estão aguardando aprovação.

CREATE VIEW pedidos_pendentes AS
SELECT id, materia_prima_id, quantidade, data_pedido, status
FROM pedido_compra
WHERE status NOT IN ('Aprovado', 'Finalizado');

-- Acelerar Consulta de Estoque
-- Melhora a performance de consultas por produto no estoque.

CREATE INDEX idx_estoque_produto ON estoque (produto_id);

-- Otimizar Consulta de Ordens de Produção
-- Acelera buscas frequentes por ordens de produção com status específico.

CREATE INDEX idx_ordem_status ON ordem_producao (status);

-- Melhorar Performance de Pedidos de Compra
-- Acelera listagens de pedidos pendentes ou concluídos.

CREATE INDEX idx_pedido_status ON pedido_compra (status);

