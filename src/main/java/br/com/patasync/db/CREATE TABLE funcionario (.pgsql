CREATE TABLE funcionario (
    funcionario_id   SERIAL PRIMARY KEY,
    pessoa_id        INTEGER NOT NULL,
    salario          NUMERIC(10,2) NOT NULL,
    tipo_funcionario VARCHAR(30) NOT NULL,
    CONSTRAINT fk_funcionario_pessoa
        FOREIGN KEY (pessoa_id) REFERENCES pessoa(pessoa_id)
);

SELECT pessoa_id, nome
FROM pessoa
ORDER BY pessoa_id;

INSERT INTO funcionario (pessoa_id, salario, tipo_funcionario)
VALUES
(10, 8000.00, 'VETERINARIO'),
(11, 8200.00, 'VETERINARIO');

SELECT f.funcionario_id, p.nome, f.tipo_funcionario
FROM funcionario f
JOIN pessoa p ON p.pessoa_id = f.pessoa_id
WHERE f.tipo_funcionario = 'VETERINARIO'
ORDER BY f.funcionario_id;
