CREATE TABLE procedimentos (
    procedimento_id   SERIAL PRIMARY KEY,
    descricao         VARCHAR(255) NOT NULL,
    tipo              VARCHAR(50) NOT NULL,
    categoria_clinica VARCHAR(50),
    valor_base        NUMERIC(10,2) NOT NULL
);