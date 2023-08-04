CREATE TABLE IF NOT EXISTS tipo_refeicao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(50) NOT NULL
);
CREATE TABLE IF NOT EXISTS refeicao (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    receita TEXT NOT NULL
);
CREATE TABLE IF NOT EXISTS planejamento (
    id INT AUTO_INCREMENT PRIMARY KEY,
    data DATE NOT NULL,
    tipo_refeicao_id INT,
    FOREIGN KEY (tipo_refeicao_id) REFERENCES tipo_refeicao (id),
    refeicao_id INT,
    FOREIGN KEY (refeicao_id) REFERENCES refeicao (id)
);