CREATE TABLE atendimentos (
    atendimento_id           SERIAL PRIMARY KEY,
    animal_id                INTEGER NOT NULL,
    responsavel_principal_id INTEGER NOT NULL,
    tipo_atendimento         VARCHAR(50) NOT NULL,
    data_hora_inicio         TIMESTAMP NOT NULL,
    data_hora_fim            TIMESTAMP,
    CONSTRAINT fk_atendimento_animal
        FOREIGN KEY (animal_id) REFERENCES animal(animal_id),
    CONSTRAINT fk_atendimento_funcionario
        FOREIGN KEY (responsavel_principal_id) REFERENCES funcionario(funcionario_id)
);