-- Tabela Cliente
CREATE TABLE Cliente (
    Cpf VARCHAR(11) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade INTEGER
);

-- Tabela Cardapio
CREATE TABLE Cardapio (
    id_prato SERIAL PRIMARY KEY,
    prato VARCHAR(100) NOT NULL,
    tipo_prato VARCHAR(50),
    preço DECIMAL(10, 2) NOT NULL
);

-- Tabela Pedido
CREATE TABLE Pedido (
    id_pedido SERIAL PRIMARY KEY,
    id_cliente VARCHAR(11) REFERENCES Cliente(Cpf),
    nome_cliente VARCHAR(100),
    preço_total DECIMAL(10, 2),
    FOREIGN KEY (id_cliente) REFERENCES Cliente(Cpf) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE pedidos (
    id_pedido SERIAL PRIMARY KEY,
    cpf VARCHAR(15) REFERENCES cliente(cpf),
    nome_cliente VARCHAR(255),
    nome_prato VARCHAR(255)
);

-- Inserir Cliente 1
INSERT INTO Cliente (Cpf, nome, idade) VALUES ('18954494700', 'Maria Eduarda', 23);

-- Inserir Cliente 2
INSERT INTO Cliente (Cpf, nome, idade) VALUES ('94571694500', 'Murilo Borges', 48);

-- Inserir Cliente 3
INSERT INTO Cliente (Cpf, nome, idade) VALUES ('03964817400', 'Felipe Almeida', 17);

-- Inserir Prato 1
INSERT INTO Cardapio (prato, tipo_prato, preço) VALUES ('Ceviche de Tilápia', 'Entrada', 35.99);

-- Inserir Prato 2
INSERT INTO Cardapio (prato, tipo_prato, preço) VALUES ('Salmão Grelhado', 'Principal', 62.50);

-- Inserir Prato 3
INSERT INTO Cardapio (prato, tipo_prato, preço) VALUES ('Torta de Morango', 'Sobremesa', 28.75);

-- Inserir Pedido 1
INSERT INTO Pedidos (cpf, nome_cliente, nome_prato) 
VALUES ('18954494700', (SELECT nome FROM Cliente WHERE Cpf = '18954494700'), (SELECT prato FROM Cardapio WHERE id_prato = 1));

-- Inserir Pedido 2
INSERT INTO Pedidos (cpf, nome_cliente, nome_prato) 
VALUES ('94571694500', (SELECT nome FROM Cliente WHERE Cpf = '94571694500'), (SELECT prato FROM Cardapio WHERE id_prato = 2));

-- Inserir Pedido 3
INSERT INTO Pedidos (cpf, nome_cliente, nome_prato) 
VALUES ('03964817400', (SELECT nome FROM Cliente WHERE Cpf = '03964817400'), (SELECT prato FROM Cardapio WHERE id_prato = 3));

-- Inserir Pedido 4
INSERT INTO Pedidos (cpf, nome_cliente, nome_prato) 
VALUES ('18954494700', (SELECT nome FROM Cliente WHERE Cpf = '18954494700'), (SELECT prato FROM Cardapio WHERE id_prato = 2));

-- Inserir Pedido 5
INSERT INTO Pedidos (cpf, nome_cliente, nome_prato) 
VALUES ('94571694500', (SELECT nome FROM Cliente WHERE Cpf = '94571694500'), (SELECT prato FROM Cardapio WHERE id_prato = 3));

-- Inserir Pedido 6
INSERT INTO Pedidos (cpf, nome_cliente, nome_prato) 
VALUES ('03964817400', (SELECT nome FROM Cliente WHERE Cpf = '03964817400'), (SELECT prato FROM Cardapio WHERE id_prato = 1));

-- Inserir Pedido 7
INSERT INTO Pedidos (cpf, nome_cliente, nome_prato) 
VALUES ('18954494700', (SELECT nome FROM Cliente WHERE Cpf = '18954494700'), (SELECT prato FROM Cardapio WHERE id_prato = 3));

-- Inserir Pedido 8
INSERT INTO Pedidos (cpf, nome_cliente, nome_prato) 
VALUES ('94571694500', (SELECT nome FROM Cliente WHERE Cpf = '94571694500'), (SELECT prato FROM Cardapio WHERE id_prato = 1));

-- Inserir Pedido 9
INSERT INTO Pedidos (cpf, nome_cliente, nome_prato) 
VALUES ('03964817400', (SELECT nome FROM Cliente WHERE Cpf = '03964817400'), (SELECT prato FROM Cardapio WHERE id_prato = 2));

