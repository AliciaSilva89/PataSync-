CREATE TABLE medicamentos (
    medicamento_id    SERIAL PRIMARY KEY,
    nome              VARCHAR(255) NOT NULL,
    principio_ativo   VARCHAR(255),
    classe_indicacao  VARCHAR(255),
    dosagem_media     VARCHAR(255),
    categoria_clinica VARCHAR(50),
    valor_unitario    NUMERIC(10,2) NOT NULL,
    observacoes       TEXT
);
