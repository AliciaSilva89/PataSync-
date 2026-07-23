CREATE TABLE pessoa (
    pessoa_id       SERIAL PRIMARY KEY,
    nome            VARCHAR(255) NOT NULL,
    cpf             VARCHAR(11)  NOT NULL UNIQUE,
    telefone        VARCHAR(50),
    email           VARCHAR(255),
    logradouro      VARCHAR(255),
    numero          VARCHAR(20),
    complemento     VARCHAR(255),
    cep             VARCHAR(20),
    cidade          VARCHAR(100),
    estado          VARCHAR(50),
    profissao       VARCHAR(100),
    data_nascimento DATE NOT NULL,
    sexo            VARCHAR(20),
    estado_civil    VARCHAR(50)
);

INSERT INTO pessoa (nome, cpf, telefone, email, cidade, estado, data_nascimento, sexo, estado_civil)
VALUES
-- Tutores (5)
('Ana Pereira',    '11111111111', '(11) 90000-0001', 'ana.pereira@example.com',    'São Paulo', 'SP', '1985-01-10', 'F', 'Solteira'),
('Bruno Santos',   '22222222222', '(11) 90000-0002', 'bruno.santos@example.com',   'São Paulo', 'SP', '1980-03-20', 'M', 'Casado'),
('Carla Oliveira', '33333333333', '(11) 90000-0003', 'carla.oliveira@example.com', 'São Paulo', 'SP', '1990-07-15', 'F', 'Casada'),
('Diego Souza',    '44444444444', '(11) 90000-0004', 'diego.souza@example.com',    'São Paulo', 'SP', '1978-11-05', 'M', 'Solteiro'),
('Eduarda Lima',   '55555555555', '(11) 90000-0005', 'eduarda.lima@example.com',   'São Paulo', 'SP', '1992-09-30', 'F', 'Solteira'),

-- Atendentes (4)
('Fernanda Atendente', '66666666666', '(11) 91000-0001', 'fernanda.at@example.com', 'São Paulo', 'SP', '1995-02-12', 'F', 'Solteira'),
('Gustavo Atendente',  '77777777777', '(11) 91000-0002', 'gustavo.at@example.com',  'São Paulo', 'SP', '1993-06-25', 'M', 'Solteiro'),
('Helena Atendente',   '88888888888', '(11) 91000-0003', 'helena.at@example.com',   'São Paulo', 'SP', '1997-04-08', 'F', 'Solteira'),
('Igor Atendente',     '99999999999', '(11) 91000-0004', 'igor.at@example.com',     'São Paulo', 'SP', '1991-12-19', 'M', 'Casado'),

-- Veterinários (2)
('João Veterinário',   '10101010101', '(11) 92000-0001', 'joao.vet@example.com',    'São Paulo', 'SP', '1980-03-20', 'M', 'Casado'),
('Luiza Veterinária',  '20202020202', '(11) 92000-0002', 'luiza.vet@example.com',   'São Paulo', 'SP', '1984-08-14', 'F', 'Casada'),

-- Assistentes de Veterinário (3)
('Marcos Assistente',  '30303030303', '(11) 93000-0001', 'marcos.ass@example.com',  'São Paulo', 'SP', '1990-01-11', 'M', 'Solteiro'),
('Nadia Assistente',   '40404040404', '(11) 93000-0002', 'nadia.ass@example.com',   'São Paulo', 'SP', '1992-05-23', 'F', 'Solteira'),
('Otávio Assistente',  '50505050505', '(11) 93000-0003', 'otavio.ass@example.com',  'São Paulo', 'SP', '1989-10-02', 'M', 'Casado');

SELECT pessoa_id, nome FROM pessoa ORDER BY pessoa_id;

SELECT pessoa_id, nome
FROM pessoa
ORDER BY pessoa_id;