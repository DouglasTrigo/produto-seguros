 CREATE TABLE CATEGORIA (
    ID INTEGER AUTO_INCREMENT PRIMARY KEY,
    DESCRICAO VARCHAR(100) NOT NULL,
    IOF REAL NOT NULL,
    PIS REAL NOT NULL,
    COFINS REAL NOT NULL,
    CONSTRAINT unique_field UNIQUE (DESCRICAO)
 );

 CREATE TABLE PRODUTO(
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NOME VARCHAR(250) NOT NULL,
    CATEGORIA_ID INTEGER NOT NULL,
    PRECO_BASE DECFLOAT NOT NULL,
    PRECO_TARIFADO DECFLOAT NOT NULL,
    FOREIGN KEY (ID) REFERENCES CATEGORIA(ID)
);