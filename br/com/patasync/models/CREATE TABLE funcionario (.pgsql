CREATE TABLE funcionario (
    funcionario_id   SERIAL PRIMARY KEY,
    nome             VARCHAR(255) NOT NULL,
    cpf              VARCHAR(11)  NOT NULL UNIQUE,
    telefone         VARCHAR(50),
    email            VARCHAR(255),
    logradouro       VARCHAR(255),
    numero           VARCHAR(20),
    complemento      VARCHAR(255),
    cep              VARCHAR(20),
    cidade           VARCHAR(100),
    estado           VARCHAR(50),
    profissao        VARCHAR(100),
    data_nascimento  DATE NOT NULL,
    sexo             VARCHAR(20),
    estado_civil     VARCHAR(50),
    salario          NUMERIC(10,2) NOT NULL,
    tipo_funcionario VARCHAR(30) NOT NULL
);