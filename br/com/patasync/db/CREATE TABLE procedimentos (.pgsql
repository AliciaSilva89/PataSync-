CREATE TABLE procedimentos (
    procedimento_id   SERIAL PRIMARY KEY,
    descricao         VARCHAR(255) NOT NULL,
    tipo              VARCHAR(50) NOT NULL,
    categoria_clinica VARCHAR(50),
    valor_base        NUMERIC(10,2) NOT NULL
);

INSERT INTO procedimentos (descricao, tipo, categoria_clinica, valor_base)
VALUES
-- Consultas
('Consulta clínica geral', 'CONSULTA', 'CLINICO_GERAL_PREVENTIVO', 120.00),
('Retorno de consulta', 'CONSULTA', 'CLINICO_GERAL_PREVENTIVO', 80.00),

-- Exames laboratoriais
('Hemograma completo', 'EXAME', 'DIAGNOSTICO', 90.00),
('Perfil bioquímico básico', 'EXAME', 'DIAGNOSTICO', 150.00),

-- Exames de imagem
('Raio-X simples', 'EXAME', 'DIAGNOSTICO', 180.00),
('Ultrassonografia abdominal', 'EXAME', 'DIAGNOSTICO', 250.00),

-- Procedimentos de rotina
('Profilaxia dentária (limpeza)', 'CIRURGIA', 'PREVENCAO_ROTINA', 300.00),
('Castração canina', 'CIRURGIA', 'CIRURGICA_ANESTESICA', 600.00),
('Castração felina', 'CIRURGIA', 'CIRURGICA_ANESTESICA', 450.00),

-- Outros procedimentos de enfermagem
('Aplicação de medicação injetável', 'EXAME', 'TERAPEUTICA_ENFERMAGEM', 40.00),
('Curativo simples', 'EXAME', 'TERAPEUTICA_ENFERMAGEM', 60.00);

SELECT procedimento_id, descricao, tipo, categoria_clinica, valor_base
FROM procedimentos
ORDER BY procedimento_id;