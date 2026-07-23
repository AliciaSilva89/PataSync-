CREATE TABLE tutor (
    tutor_id   SERIAL PRIMARY KEY,
    pessoa_id  INTEGER NOT NULL,
    CONSTRAINT fk_tutor_pessoa
        FOREIGN KEY (pessoa_id) REFERENCES pessoa(pessoa_id)
);

INSERT INTO tutor (pessoa_id)
VALUES
(1),
(2),
(3),
(4),
(5);

SELECT t.tutor_id, p.nome
FROM tutor t
JOIN pessoa p ON p.pessoa_id = t.pessoa_id
ORDER BY t.tutor_id;
