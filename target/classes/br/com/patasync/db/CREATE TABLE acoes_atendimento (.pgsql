CREATE TABLE acoes_atendimento (
    acao_id                   SERIAL PRIMARY KEY,
    atendimento_id            INTEGER NOT NULL,
    funcionario_id            INTEGER NOT NULL,
    tipo_acao                 VARCHAR(50) NOT NULL,
    categoria_clinica         VARCHAR(50),
    descricao                 TEXT,
    data_hora                 TIMESTAMP NOT NULL,
    procedimento_realizado_id INTEGER,
    medicacao_aplicada_id     INTEGER,
    CONSTRAINT fk_acao_atendimento
        FOREIGN KEY (atendimento_id) REFERENCES atendimentos(atendimento_id),
    CONSTRAINT fk_acao_funcionario
        FOREIGN KEY (funcionario_id) REFERENCES funcionario(funcionario_id),
    CONSTRAINT fk_acao_proc_realizado
        FOREIGN KEY (procedimento_realizado_id) REFERENCES procedimentos_realizados(procedimento_realizado_id),
    CONSTRAINT fk_acao_medicacao_aplicada
        FOREIGN KEY (medicacao_aplicada_id) REFERENCES medicacoes_aplicadas(medicacao_aplicada_id)
);