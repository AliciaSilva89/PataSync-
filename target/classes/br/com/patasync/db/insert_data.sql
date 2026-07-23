-- Inserir dados na tabela pessoa
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

-- Inserir dados na tabela tutor
INSERT INTO tutor (pessoa_id)
VALUES
(1),
(2),
(3),
(4),
(5);

-- Inserir dados na tabela funcionario
INSERT INTO funcionario (pessoa_id, salario, tipo_funcionario)
VALUES
(6, 2000.00, 'ATENDENTE'),
(7, 2500.00, 'ASSISTENTE'),
(8, 2500.00, 'ASSISTENTE'),
(9, 2500.00, 'ASSISTENTE'),
(10, 8000.00, 'VETERINARIO'),
(11, 8200.00, 'VETERINARIO');

-- Inserir dados na tabela assistente_veterinario
INSERT INTO assistente_veterinario (funcionario_id)
VALUES
(7),
(8),
(9);

-- Inserir dados na tabela animal
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

-- Inserir dados na tabela procedimentos
INSERT INTO procedimentos (descricao, tipo, categoria_clinica, valor_base)
VALUES
-- Consultas
('Consulta clínica geral', 'CONSULTA', 'CLINICO_GERAL_PREVENTIVO', 120.00),
('Consulta dermatológica', 'CONSULTA', 'DERMATOLOGIA', 150.00),
('Consulta oftalmológica', 'CONSULTA', 'OFTALMOLOGIA', 140.00),

-- Exames
('Hemograma completo', 'EXAME', 'LABORATORIAL', 80.00),
('Ultrassonografia abdominal', 'EXAME', 'IMAGEM', 200.00),
('Radiografia', 'EXAME', 'IMAGEM', 120.00),

-- Procedimentos cirúrgicos
('Castração (cão)', 'CIRURGIA', 'CIRURGICO', 400.00),
('Castração (gato)', 'CIRURGIA', 'CIRURGICO', 300.00),
('Limpeza dentária', 'CIRURGIA', 'ODONTOLOGIA', 250.00);

-- Inserir dados na tabela medicamentos
INSERT INTO medicamentos
    (nome, principio_ativo, classe_indicacao, dosagem_media, categoria_clinica, valor_unitario, observacoes)
VALUES
-- Analgésico / antitérmico
('Dipirona', 'Dipirona sódica', 'Analgésico / antitérmico', '25 mg/kg', 'CLINICO_GERAL_PREVENTIVO', 5.50, 'Uso veterinário'),
('Meloxicam', 'Meloxicam', 'Anti-inflamatório', '0.2 mg/kg', 'CLINICO_GERAL_PREVENTIVO', 15.00, 'Uso veterinário'),

-- Antibióticos
('Amoxicilina', 'Amoxicilina', 'Antibiótico', '20 mg/kg', 'CLINICO_GERAL_PREVENTIVO', 8.00, 'Uso veterinário'),
('Enrofloxacina', 'Enrofloxacina', 'Antibiótico', '5 mg/kg', 'CLINICO_GERAL_PREVENTIVO', 12.00, 'Uso veterinário'),

-- Antiparasitários
('Doramectina', 'Doramectina', 'Antiparasitário', '0.2 mg/kg', 'CLINICO_GERAL_PREVENTIVO', 25.00, 'Uso veterinário'),
('Praziquantel', 'Praziquantel', 'Antiparasitário', '5 mg/kg', 'CLINICO_GERAL_PREVENTIVO', 10.00, 'Uso veterinário');
