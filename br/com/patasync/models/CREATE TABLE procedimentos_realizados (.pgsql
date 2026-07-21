CREATE TABLE procedimentos_realizados (
    procedimento_realizado_id SERIAL PRIMARY KEY,
    atendimento_id            INTEGER NOT NULL,
    procedimento_id           INTEGER NOT NULL,
    valor_base                NUMERIC(10,2) NOT NULL,
    CONSTRAINT fk_proc_realizado_atendimento
        FOREIGN KEY (atendimento_id) REFERENCES atendimentos(atendimento_id),
    CONSTRAINT fk_proc_realizado_procedimento
        FOREIGN KEY (procedimento_id) REFERENCES procedimentos(procedimento_id)
);