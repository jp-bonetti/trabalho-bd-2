
CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    preco NUMERIC NOT NULL,
    categoria VARCHAR(50)
);


CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    contato VARCHAR(100),
    endereco TEXT
);


CREATE TABLE fornecedor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    contato VARCHAR(100),
    endereco TEXT
);


CREATE TABLE materia_prima (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    quantidade NUMERIC NOT NULL,
    unidade VARCHAR(10) NOT NULL,
    fornecedor_id INTEGER REFERENCES fornecedor(id) ON DELETE SET NULL
);


CREATE TABLE lista_materiais (
    produto_id INTEGER NOT NULL,
    materia_prima_id INTEGER NOT NULL,
    quantidade NUMERIC NOT NULL,
    PRIMARY KEY (produto_id, materia_prima_id),
    FOREIGN KEY (produto_id) REFERENCES produto(id) ON DELETE CASCADE,
    FOREIGN KEY (materia_prima_id) REFERENCES materia_prima(id) ON DELETE CASCADE
);


CREATE TABLE pedido_compra (
    id SERIAL PRIMARY KEY,
    fornecedor_id INTEGER REFERENCES fornecedor(id) ON DELETE SET NULL,
    data_pedido TIMESTAMP DEFAULT now(),
    status VARCHAR(20) NOT NULL CHECK (status IN ('PENDENTE', 'APROVADO', 'RECEBIDO', 'CANCELADO'))
);


CREATE TABLE item_pedido_compra (
    id SERIAL PRIMARY KEY,
    pedido_compra_id INTEGER REFERENCES pedido_compra(id) ON DELETE CASCADE,
    materia_prima_id INTEGER REFERENCES materia_prima(id),
    quantidade NUMERIC NOT NULL,
    preco_unitario NUMERIC,
    unidade VARCHAR(10)
);


CREATE TABLE ordem_producao (
    id SERIAL PRIMARY KEY,
    produto_id INTEGER REFERENCES produto(id) ON DELETE CASCADE,
    quantidade INTEGER NOT NULL,
    data_inicio TIMESTAMP,
    data_fim TIMESTAMP,
    status VARCHAR(20) NOT NULL CHECK (status IN ('PENDENTE', 'EM_ANDAMENTO', 'FINALIZADA', 'CANCELADA')),
    data_criacao TIMESTAMP DEFAULT now()
);


CREATE TABLE pedido_venda (
    id SERIAL PRIMARY KEY,
    cliente_id INTEGER REFERENCES cliente(id) ON DELETE SET NULL,
    produto_id INTEGER REFERENCES produto(id),
    quantidade INTEGER NOT NULL,
    data_pedido TIMESTAMP DEFAULT now(),
    status VARCHAR(20) NOT NULL CHECK (status IN ('PENDENTE', 'EM_SEPARACAO', 'ENVIADO', 'ENTREGUE', 'CANCELADO'))
);


CREATE TABLE estoque (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('PRODUTO', 'MATERIA_PRIMA')),
    referencia_id INTEGER NOT NULL,
    quantidade NUMERIC NOT NULL,
    localizacao VARCHAR(100)
);


CREATE TABLE movimentacao_estoque (
    id SERIAL PRIMARY KEY,
    tipo VARCHAR(10) NOT NULL CHECK (tipo IN ('ENTRADA', 'SAIDA')),
    produto_id INTEGER REFERENCES produto(id) ON DELETE SET NULL,
    materia_prima_id INTEGER REFERENCES materia_prima(id) ON DELETE SET NULL,
    quantidade NUMERIC NOT NULL,
    data TIMESTAMP DEFAULT now(),
    origem VARCHAR(30) CHECK (origem IN ('pedido_compra', 'pedido_venda', 'ordem_producao', 'ajuste')),
    referencia_id INTEGER 
);

