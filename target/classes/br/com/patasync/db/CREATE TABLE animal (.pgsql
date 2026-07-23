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
SELECT t.tutor_id, p.nome
FROM tutor t
JOIN pessoa p ON p.pessoa_id = t.pessoa_id
ORDER BY t.tutor_id;

INSERT INTO animal (nome, especie, raca, idade, peso, tutor_id)
VALUES
-- Animais da Ana (tutor_id 1)
('Rex',        'Cão',  'SRD',                 5, 18.5, 1),
('Mia',        'Gato', 'Sem raça definida',   3, 4.2, 1),

-- Animais do Bruno (tutor_id 2)
('Thor',       'Cão',  'Labrador',           2, 28.0, 2),

-- Animais da Carla (tutor_id 3)
('Belinha',    'Cão',  'Shih Tzu',           4, 6.3, 3),

-- Animais do Diego (tutor_id 4)
('Cookie',     'Gato', 'Persa',              1, 3.1, 4),

-- Animais da Eduarda (tutor_id 5)
('Luna',       'Gato', 'Siamês',             2, 3.8, 5);

SELECT a.animal_id, a.nome AS animal, a.especie, p.nome AS tutor
FROM animal a
JOIN tutor t   ON t.tutor_id = a.tutor_id
JOIN pessoa p ON p.pessoa_id = t.pessoa_id
ORDER BY a.animal_id;

SELECT
    a.animal_id,
    a.nome       AS animal,
    a.especie,
    a.raca,
    a.idade,
    a.peso,
    p.nome       AS tutor
FROM animal a
JOIN tutor t   ON t.tutor_id     = a.tutor_id
JOIN pessoa p  ON p.pessoa_id    = t.pessoa_id
ORDER BY a.animal_id;