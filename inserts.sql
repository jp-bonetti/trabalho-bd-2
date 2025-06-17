INSERT INTO produto (nome, descricao, preco, categoria) VALUES
('Cadeira Ergonômica', 'Cadeira com apoio lombar e ajuste de altura.', 450.00, 'Mobiliário'),
('Mesa de Escritório', 'Mesa de madeira com gavetas.', 800.00, 'Mobiliário');

INSERT INTO fornecedor (nome, contato, endereco) VALUES
('Madeiras Brasil', 'madeiras@fornecedores.com', 'Rua das Árvores, 123'),
('Ferragens Pro', 'contato@ferragenspro.com', 'Av. Industrial, 456');

INSERT INTO materia_prima (nome, quantidade, unidade, fornecedor_id) VALUES
('Madeira MDF', 100.0, 'm²', 1),
('Parafuso 4cm', 1000, 'un', 2),
('Cola de madeira', 50.0, 'L', 2);

-- Produto 1: Cadeira Ergonômica
INSERT INTO lista_materiais (produto_id, materia_prima_id, quantidade) VALUES
(1, 1, 2.5), -- 2.5 m² de MDF
(1, 2, 8),   -- 8 parafusos
(1, 3, 0.2); -- 200 ml de cola

-- Produto 2: Mesa
INSERT INTO lista_materiais (produto_id, materia_prima_id, quantidade) VALUES
(2, 1, 4.0),
(2, 2, 12),
(2, 3, 0.3);

INSERT INTO cliente (nome, contato, endereco) VALUES
('Empresa Alpha', 'compras@alpha.com', 'Av. Central, 999'),
('Loja Beta', 'contato@betastore.com', 'Rua B, 321');

INSERT INTO pedido_venda (cliente_id, produto_id, quantidade, status) VALUES
(1, 1, 5, 'PENDENTE'),
(2, 2, 2, 'EM_SEPARACAO');

INSERT INTO pedido_compra (fornecedor_id, status) VALUES
(1, 'PENDENTE'),
(2, 'APROVADO');

INSERT INTO item_pedido_compra (pedido_compra_id, materia_prima_id, quantidade, preco_unitario, unidade) VALUES
(1, 1, 20.0, 60.0, 'm²'),
(2, 2, 200, 0.20, 'un'),
(2, 3, 5.0, 10.00, 'L');

INSERT INTO ordem_producao (produto_id, quantidade, data_inicio, status) VALUES
(1, 10, NOW(), 'EM_ANDAMENTO'),
(2, 5, NOW(), 'PENDENTE');

-- Produtos
INSERT INTO estoque (tipo, referencia_id, quantidade, localizacao) VALUES
('PRODUTO', 1, 3, 'A1'),  -- Cadeiras
('PRODUTO', 2, 1, 'A2');  -- Mesas

-- Matérias-primas
INSERT INTO estoque (tipo, referencia_id, quantidade, localizacao) VALUES
('MATERIA_PRIMA', 1, 80.0, 'M1'), -- MDF
('MATERIA_PRIMA', 2, 500, 'M2'),  -- Parafusos
('MATERIA_PRIMA', 3, 20.0, 'M3'); -- Cola


-- Entrada por pedido de compra
INSERT INTO movimentacao_estoque (tipo, materia_prima_id, quantidade, origem, referencia_id) VALUES
('ENTRADA', 1, 20.0, 'PEDIDO_COMPRA', 1),
('ENTRADA', 2, 200, 'PEDIDO_COMPRA', 2);

-- Saída para produção
INSERT INTO movimentacao_estoque (tipo, materia_prima_id, quantidade, origem, referencia_id) VALUES
('SAIDA', 1, 10.0, 'ORDEM_PRODUCAO', 1),
('SAIDA', 2, 80, 'ORDEM_PRODUCAO', 1);

