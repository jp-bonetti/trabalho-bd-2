-- Inserindo produtos
INSERT INTO produto (nome, descricao, preco, categoria) VALUES
('Camisa Social Slim', 'Camisa social de algodão com corte slim', 149.99, 'Camisas'),
('Calça Jeans Skinny', 'Calça jeans masculina com corte ajustado', 189.99, 'Calças'),
('Blazer Casual', 'Blazer leve para ocasiões formais e casuais', 299.99, 'Blazers'),
('Bermuda Chino', 'Bermuda de sarja confortável e estilosa', 119.99, 'Bermudas'),
('Jaqueta de Couro', 'Jaqueta clássica de couro legítimo', 499.99, 'Jaquetas'),
('Suéter de Lã', 'Suéter elegante de lã merino', 249.99, 'Suéteres'),
('Tênis Casual', 'Tênis de couro para o dia a dia', 229.99, 'Calçados'),
('Meia Social', 'Meia de algodão com fios de qualidade', 39.99, 'Acessórios'),
('Cinto de Couro', 'Cinto legítimo com fivela metálica', 99.99, 'Acessórios'),
('Gravata Seda', 'Gravata de seda pura', 149.99, 'Acessórios');

-- Inserindo fornecedores
INSERT INTO fornecedor (nome, contato, endereco) VALUES
('Tecidos Luxo', 'contato@luxotecidos.com', 'Rua das Fibras, 123'),
('Fios & Tramas', 'contato@fioseetramas.com', 'Av. dos Tecidos, 456'),
('Couro Premium', 'contato@couropremium.com', 'Rua do Couro, 789'),
('Moda Sustentável', 'contato@modasustentavel.com', 'Av. Verde, 101'),
('Tecidos Nobres', 'contato@tecidosnobres.com', 'Rua Elegante, 202'),
('Matéria-Prima Forte', 'contato@mpforte.com', 'Av. Industrial, 303'),
('Componentes de Qualidade', 'contato@compqualidade.com', 'Rua dos Materiais, 404'),
('Botões e Fechos', 'contato@botoesfechos.com', 'Av. dos Acessórios, 505'),
('Estamparia Criativa', 'contato@estampariacriativa.com', 'Rua das Estampas, 606'),
('Zíper e Aviamentos', 'contato@ziperaviamentos.com', 'Av. dos Detalhes, 707');

-- Inserindo matéria-prima
INSERT INTO materia_prima (nome, quantidade, unidade, fornecedor_id) VALUES
('Tecido Algodão', 1000, 'm²', 1),
('Tecido Jeans', 1200, 'm²', 2),
('Tecido de Lã', 800, 'm²', 3),
('Tecido Sarja', 600, 'm²', 4),
('Couro Legítimo', 300, 'm²', 5),
('Linha de Costura', 500, 'kg', 6),
('Botões de Metal', 700, 'un', 7),
('Zíper Industrial', 400, 'un', 8),
('Estampas Personalizadas', 500, 'm²', 9),
('Aviamentos Diversos', 600, 'un', 10);

-- Inserindo pedidos de compra
INSERT INTO pedido_compra (materia_prima_id, quantidade, data_pedido, status) VALUES
(1, 500, '2025-06-01 10:00:00', 'Aprovado'),
(2, 700, '2025-06-02 14:00:00', 'Pendente'),
(3, 300, '2025-06-03 09:00:00', 'Aprovado'),
(4, 450, '2025-06-05 11:00:00', 'Em processamento'),
(5, 200, '2025-06-07 15:00:00', 'Aprovado'),
(6, 350, '2025-06-08 08:00:00', 'Pendente'),
(7, 600, '2025-06-10 17:00:00', 'Em processamento'),
(8, 800, '2025-06-12 12:00:00', 'Pendente'),
(9, 500, '2025-06-14 14:00:00', 'Aprovado'),
(10, 300, '2025-06-16 10:00:00', 'Em processamento');

-- Inserindo estoque
INSERT INTO estoque (produto_id, quantidade, localizacao) VALUES
(1, 250, 'Galpão A'),
(2, 300, 'Galpão B'),
(3, 150, 'Galpão C'),
(4, 200, 'Galpão D'),
(5, 100, 'Galpão E'),
(6, 180, 'Galpão F'),
(7, 220, 'Galpão G'),
(8, 400, 'Galpão H'),
(9, 320, 'Galpão I'),
(10, 150, 'Galpão J');

-- Inserindo lista de materiais
INSERT INTO lista_materiais (produto_id, materia_prima_id, quantidade) VALUES
(1, 1, 2.5),
(2, 2, 3.0),
(3, 3, 4.0),
(4, 4, 2.8),
(5, 5, 1.5),
(6, 6, 3.2),
(7, 7, 1.0),
(8, 8, 2.0),
(9, 9, 2.5),
(10, 10, 1.8);

-- Inserindo clientes
INSERT INTO cliente (nome, contato, endereco) VALUES
('João Silva', 'joao@email.com', 'Rua do Comércio, 210'),
('Maria Souza', 'maria@email.com', 'Praça Central, 45'),
('Carlos Lima', 'carlos@email.com', 'Avenida das Nações, 78'),
('Fernanda Alves', 'fernanda@email.com', 'Rua da Moda, 89'),
('Ricardo Mendes', 'ricardo@email.com', 'Travessa das Indústrias, 101'),
('Juliana Ferreira', 'juliana@email.com', 'Rua dos Tecidos, 202'),
('Marcelo Santos', 'marcelo@email.com', 'Alameda Fashion, 303'),
('Patrícia Ramos', 'patricia@email.com', 'Avenida das Estampas, 404'),
('Gabriel Costa', 'gabriel@email.com', 'Rua do Design, 505'),
('Renata Oliveira', 'renata@email.com', 'Alameda da Costura, 606');

-- Inserindo ordens de produção
INSERT INTO ordem_producao (produto_id, quantidade, data_inicio, data_fim, status) VALUES
(1, 500, '2025-06-01 08:00:00', '2025-06-15 18:00:00', 'Em andamento'),
(2, 700, '2025-06-02 09:00:00', '2025-06-16 17:00:00', 'Planejado'),
(3, 300, '2025-06-05 10:00:00', '2025-06-20 18:00:00', 'Em andamento'),
(4, 450, '2025-06-07 08:00:00', '2025-06-18 17:00:00', 'Planejado'),
(5, 200, '2025-06-10 09:00:00', '2025-06-25 18:00:00', 'Planejado'),
(6, 350, '2025-06-12 08:00:00', '2025-06-28 17:00:00', 'Planejado'),
(7, 600, '2025-06-15 09:00:00', '2025-07-01 18:00:00', 'Planejado'),
(8, 800, '2025-06-17 08:00:00', '2025-07-05 17:00:00', 'Planejado'),
(9, 500, '2025-06-20 09:00:00', '2025-07-10 18:00:00', 'Planejado'),
(10, 300, '2025-06-22 08:00:00', '2025-07-15 17:00:00', 'Planejado');
