CREATE TABLE medicamentos (
    medicamento_id    SERIAL PRIMARY KEY,
    nome              VARCHAR(255) NOT NULL,
    principio_ativo   VARCHAR(255),
    classe_indicacao  VARCHAR(255),
    dosagem_media     VARCHAR(255),
    categoria_clinica VARCHAR(50),
    valor_unitario    NUMERIC(10,2) NOT NULL,
    observacoes       TEXT
);

INSERT INTO medicamentos
    (nome, principio_ativo, classe_indicacao, dosagem_media, categoria_clinica, valor_unitario, observacoes)
VALUES
-- Analgésico / antitérmico
('Dipirona', 'Dipirona sódica', 'Analgésico e antitérmico', '25 mg/kg', 'URGENCIA', 15.00,
 'Usar com cautela em gatos e animais com distúrbios hematológicos.'),

-- Antiinflamatório não esteroidal
('Meloxicam', 'Meloxicam', 'Antiinflamatório não esteroidal', '0,1 mg/kg', 'PREVENCAO_ROTINA', 25.00,
 'Evitar em pacientes com insuficiência renal ou desidratados.'),

-- Antibiótico
('Amoxicilina + Clavulanato', 'Amoxicilina + Ácido clavulânico', 'Antibiótico de amplo espectro', '12,5 mg/kg', 'TERAPEUTICA_ENFERMAGEM', 30.00,
 'Indicado para infecções de pele, trato respiratório e urinário.'),

-- Antiparasitário interno
('Vermífugo polivalente', 'Múltiplas substâncias antiparasitárias', 'Antiparasitário interno', 'Dose conforme peso e bula', 'PREVENCAO_ROTINA', 40.00,
 'Profilaxia periódica contra vermes intestinais.'),

-- Antiparasitário externo
('Bravecto (Fluralaner)', 'Fluralaner', 'Antiparasitário externo sistêmico', 'Dose única conforme peso', 'PREVENCAO_ROTINA', 120.00,
 'Controle de pulgas e carrapatos por até 12 semanas.');

 SELECT medicamento_id, nome, classe_indicacao, valor_unitario
FROM medicamentos
ORDER BY medicamento_id;