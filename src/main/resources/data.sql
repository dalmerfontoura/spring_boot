insert into categoria(nome) values('Informática')
insert into categoria(nome) values('Escritório')
insert into categoria(nome) values('Cama, Mesa e Banho')
insert into categoria(nome) values('Eletronicos')
insert into categoria(nome) values('Jardinagem')
insert into categoria(nome) values('Decoração')
insert into categoria(nome) values('Perfumaria')

INSERT INTO PRODUTO (NOME, PRECO) VALUES ('Computador', 2000)
INSERT INTO PRODUTO (NOME, PRECO) VALUES ('Impressora', 800)
INSERT INTO PRODUTO (NOME, PRECO) VALUES ('Mouse', 80)

INSERT INTO PRODUTO_CATEGORIA (PRODUTO_ID, CATEGORIA_ID) VALUES (1, 1)
INSERT INTO PRODUTO_CATEGORIA (PRODUTO_ID, CATEGORIA_ID) VALUES (2, 1)
INSERT INTO PRODUTO_CATEGORIA (PRODUTO_ID, CATEGORIA_ID) VALUES (3, 1)

INSERT INTO PRODUTO_CATEGORIA (PRODUTO_ID, CATEGORIA_ID) VALUES (2, 2)

INSERT INTO ESTADO (NOME) VALUES('Minas Gerais')
INSERT INTO ESTADO (NOME) VALUES('São Paulo')

INSERT INTO CIDADE (NOME, ESTADO_ID) VALUES ('Uberlandia', 1)
INSERT INTO CIDADE (NOME, ESTADO_ID) VALUES ('São Paulo', 2)
INSERT INTO CIDADE (NOME, ESTADO_ID) VALUES ('Campinas', 2)
