CREATE DATABASE IF NOT EXISTS show_do_milhao
    CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE show_do_milhao;

CREATE TABLE jogador (
    id        INT AUTO_INCREMENT PRIMARY KEY,
    nome      VARCHAR(100) NOT NULL UNIQUE,
    pontuacao INT DEFAULT 0
);

CREATE TABLE historico_jogo (
    id         INT AUTO_INCREMENT PRIMARY KEY,
    jogador_id INT NOT NULL,
    premio     INT NOT NULL,
    data_jogo  DATETIME DEFAULT NOW(),
    FOREIGN KEY (jogador_id) REFERENCES jogador(id) ON DELETE CASCADE
);

CREATE TABLE partida (
    id           INT AUTO_INCREMENT PRIMARY KEY,
    jogador1_id  INT NOT NULL,
    jogador2_id  INT NOT NULL,
    vencedor_id  INT,
    data_partida DATETIME DEFAULT NOW(),
    FOREIGN KEY (jogador1_id) REFERENCES jogador(id) ON DELETE CASCADE,
    FOREIGN KEY (jogador2_id) REFERENCES jogador(id) ON DELETE CASCADE,
    FOREIGN KEY (vencedor_id) REFERENCES jogador(id) ON DELETE SET NULL
);