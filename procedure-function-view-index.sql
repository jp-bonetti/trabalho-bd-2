-- =============================
-- 1. Função: registrar_movimentacao
-- Centraliza a lógica de movimentação de estoque
-- Usada para registrar entradas e saídas de produto ou matéria-prima
-- =============================
CREATE OR REPLACE FUNCTION registrar_movimentacao(
    tipo_movimentacao VARCHAR,
    produto_id INT,
    materia_prima_id INT,
    quantidade NUMERIC,
    origem VARCHAR,
    referencia_id INT
)
RETURNS VOID AS $$
BEGIN
    INSERT INTO movimentacao_estoque (
        tipo, produto_id, materia_prima_id, quantidade, data, origem, referencia_id
    )
    VALUES (
        tipo_movimentacao, produto_id, materia_prima_id, quantidade, now(), origem, referencia_id
    );
END;
$$ LANGUAGE plpgsql;

-- =============================
-- 2. Função: estoque_baixo
-- Verifica se uma matéria-prima está abaixo de um limite mínimo
-- Útil para alertas automáticos e planejamento de compras
-- =============================
CREATE OR REPLACE FUNCTION estoque_baixo(materia_id INT, limite NUMERIC)
RETURNS BOOLEAN AS $$
DECLARE
    qtd NUMERIC;
BEGIN
    SELECT quantidade INTO qtd FROM estoque
    WHERE tipo = 'MATERIA_PRIMA' AND referencia_id = materia_id;

    RETURN qtd IS NOT NULL AND qtd < limite;
END;
$$ LANGUAGE plpgsql;

-- =============================
-- 3. Procedure: finalizar_pedido_venda
-- Atualiza o status do pedido de venda e reduz o estoque correspondente
-- Também registra a saída na movimentação de estoque
-- =============================
CREATE OR REPLACE FUNCTION finalizar_pedido_venda(pedido_id INT)
RETURNS VOID AS $$
DECLARE
    prod_id INT;
    qtd INT;
BEGIN
    UPDATE pedido_venda SET status = 'ENTREGUE' WHERE id = pedido_id;

    SELECT produto_id, quantidade INTO prod_id, qtd FROM pedido_venda WHERE id = pedido_id;

    UPDATE estoque
    SET quantidade = quantidade - qtd
    WHERE tipo = 'PRODUTO' AND referencia_id = prod_id;

    PERFORM registrar_movimentacao('SAIDA', prod_id, NULL, qtd, 'pedido_venda', pedido_id);
END;
$$ LANGUAGE plpgsql;

-- =============================
-- 4. Trigger: atualizar_estoque_apos_compra
-- Após inserção de item de compra, atualiza estoque de matéria-prima
-- Também registra movimentação de entrada no estoque
-- =============================
CREATE OR REPLACE FUNCTION atualizar_estoque_apos_compra()
RETURNS TRIGGER AS $$
BEGIN
    IF EXISTS (
        SELECT 1 FROM estoque
        WHERE tipo = 'MATERIA_PRIMA' AND referencia_id = NEW.materia_prima_id
    ) THEN
        UPDATE estoque
        SET quantidade = quantidade + NEW.quantidade
        WHERE tipo = 'MATERIA_PRIMA' AND referencia_id = NEW.materia_prima_id;
    ELSE
        INSERT INTO estoque (tipo, referencia_id, quantidade, localizacao)
        VALUES ('MATERIA_PRIMA', NEW.materia_prima_id, NEW.quantidade, 'Automática');
    END IF;

    PERFORM registrar_movimentacao('ENTRADA', NULL, NEW.materia_prima_id, NEW.quantidade, 'pedido_compra', NEW.pedido_compra_id);

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_atualizar_estoque
AFTER INSERT ON item_pedido_compra
FOR EACH ROW EXECUTE FUNCTION atualizar_estoque_apos_compra();

-- =============================
-- 5. VIEW: vw_ordens_producao_detalhes
-- Apresenta ordens de produção com nome do produto
-- Ideal para listagens e dashboards
-- =============================
CREATE OR REPLACE VIEW vw_ordens_producao_detalhes AS
SELECT 
    op.id AS ordem_id,
    p.nome AS produto,
    op.quantidade,
    op.data_inicio,
    op.data_fim,
    op.status
FROM ordem_producao op
JOIN produto p ON op.produto_id = p.id;

-- =============================
-- 6. VIEW: vw_estoque_materias_primas
-- Mostra estoque atual de matérias-primas com dados do fornecedor
-- Usada em relatórios e planejamento de compras
-- =============================
CREATE OR REPLACE VIEW vw_estoque_materias_primas AS
SELECT 
    m.nome AS materia_prima,
    e.quantidade,
    m.unidade,
    f.nome AS fornecedor
FROM estoque e
JOIN materia_prima m ON e.referencia_id = m.id
LEFT JOIN fornecedor f ON m.fornecedor_id = f.id
WHERE e.tipo = 'MATERIA_PRIMA';

-- =============================
-- 7. VIEW: vw_historico_movimentacoes
-- Histórico completo de entradas e saídas, com nome do item
-- Útil para auditoria e rastreabilidade
-- =============================
CREATE OR REPLACE VIEW vw_historico_movimentacoes AS
SELECT 
    m.id,
    m.tipo,
    COALESCE(p.nome, mp.nome) AS item,
    m.quantidade,
    m.origem,
    m.referencia_id,
    m.data
FROM movimentacao_estoque m
LEFT JOIN produto p ON m.produto_id = p.id
LEFT JOIN materia_prima mp ON m.materia_prima_id = mp.id;

-- =============================
-- 8. ÍNDICES para Performance
-- Aceleram joins e filtros frequentes em tabelas grandes
-- =============================
CREATE INDEX idx_estoque_tipo_referencia ON estoque(tipo, referencia_id);
CREATE INDEX idx_pedido_venda_cliente ON pedido_venda(cliente_id);
CREATE INDEX idx_ordem_produto ON ordem_producao(produto_id);
CREATE INDEX idx_materia_fornecedor ON materia_prima(fornecedor_id);
CREATE INDEX idx_movimentacao_data ON movimentacao_estoque(data);

