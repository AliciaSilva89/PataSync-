CREATE TABLE animal (
    animal_id   SERIAL PRIMARY KEY,
    nome        VARCHAR(255) NOT NULL,
    especie     VARCHAR(100) NOT NULL,
    raca        VARCHAR(100),
    idade       INTEGER,
    peso        NUMERIC(10,2),
    tutor_id    INTEGER NOT NULL,
    CONSTRAINT fk_animal_tutor
        FOREIGN KEY (tutor_id) REFERENCES tutor(tutor_id)
);