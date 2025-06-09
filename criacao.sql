CREATE TABLE produto (
    id SERIAL PRIMARY KEY,
    nome TEXT NOT NULL,
    descricao TEXT,
    preco NUMERIC NOT NULL,
    categoria TEXT
);

CREATE TABLE ordem_producao (
    id SERIAL PRIMARY KEY,
    produto_id INTEGER REFERENCES produto(id),
    quantidade INTEGER NOT NULL,
    data_inicio TIMESTAMP,
    data_fim TIMESTAMP,
    status TEXT NOT NULL
);

CREATE TABLE fornecedor (
    id SERIAL PRIMARY KEY,
    nome TEXT NOT NULL,
    contato TEXT,
    endereco TEXT
);

CREATE TABLE materia_prima (
    id SERIAL PRIMARY KEY,
    nome TEXT NOT NULL,
    quantidade NUMERIC NOT NULL,
    unidade TEXT NOT NULL,
    fornecedor_id INTEGER REFERENCES fornecedor(id)
);

CREATE TABLE pedido_compra (
    id SERIAL PRIMARY KEY,
    materia_prima_id INTEGER REFERENCES materia_prima(id),
    quantidade INTEGER NOT NULL,
    data_pedido TIMESTAMP,
    status TEXT NOT NULL
);

CREATE TABLE estoque (
    id SERIAL PRIMARY KEY,
    produto_id INTEGER REFERENCES produto(id),
    quantidade INTEGER NOT NULL,
    localizacao TEXT
);

CREATE TABLE lista_materiais (
    id SERIAL PRIMARY KEY,
    produto_id INTEGER REFERENCES produto(id),
    materia_prima_id INTEGER REFERENCES materia_prima(id),
    quantidade NUMERIC NOT NULL
);

CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nome TEXT NOT NULL,
    contato TEXT,
    endereco TEXT
);

CREATE TABLE pedido_venda (
    id SERIAL PRIMARY KEY,
    cliente_id INTEGER REFERENCES cliente(id),
    produto_id INTEGER REFERENCES produto(id),
    quantidade INTEGER NOT NULL,
    data_pedido TIMESTAMP,
    status TEXT NOT NULL
);