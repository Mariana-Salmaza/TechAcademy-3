create database game;
use game;

CREATE TABLE IF NOT EXISTS scenes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS scene_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    scene_id INT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    is_interactable BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (scene_id) REFERENCES scenes(id)
);

CREATE TABLE IF NOT EXISTS inventory_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    player_name VARCHAR(100),
    name VARCHAR(100) NOT NULL,
    description TEXT
);

CREATE TABLE IF NOT EXISTS player_progress (
    id INT AUTO_INCREMENT PRIMARY KEY,
    player_name VARCHAR(100) NOT NULL,
    current_scene_id INT,
    FOREIGN KEY (current_scene_id) REFERENCES scenes(id)
);

CREATE TABLE IF NOT EXISTS commands (
    id INT AUTO_INCREMENT PRIMARY KEY,
    command_name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL
);

INSERT INTO scenes (description) VALUES
('Você está na sala de entrada de um velho castelo. O lugar está empoeirado e mal iluminado. Há uma porta ao norte e uma escada que desce para o porão.'),
('O corredor está sombrio e o vento parece sussurrar segredos antigos. As paredes estão cobertas de tapeçarias rasgadas e há um odor de mofo no ar. Há portas a leste e a oeste.'),
('Esta sala está repleta de ouro, joias e itens antigos. No centro, há um pedestal com uma caixa trancada.'),
('A biblioteca secreta é cheia de livros e pergaminhos antigos. A iluminação é fraca e há uma sensação de mistério no ar. Há uma mesa com uma vela e uma escada que leva a um compartimento secreto.'),
('Você entra em uma câmara misteriosa com um pedestal no centro. Em cima do pedestal, há um artefato brilhante envolto em uma aura mágica. A sala está protegida por um enigma que você precisa resolver para pegar o artefato.');

INSERT INTO scene_items (scene_id, name, description, is_interactable) VALUES
(1, 'Lanterna', 'Uma lanterna antiga, ainda com um pouco de óleo.', TRUE),
(1, 'Chave de Ferro', 'Uma pequena chave enferrujada, aparentemente usada para trancar algo.', TRUE),
(2, 'Diário do Conde', 'Um velho diário que fala sobre os segredos do castelo.', TRUE),
(2, 'Mapa do Castelo', 'Um mapa antigo que pode ajudar a explorar o castelo.', TRUE),
(3, 'Caixa de Ouro', 'Uma caixa trancada com um mecanismo complicado.', TRUE),
(3, 'Espada de Prata', 'Uma espada antiga com inscrições mágicas.', TRUE),
(4, 'Livro de Feitiços', 'Um livro que contém feitiços antigos e rituais.', TRUE),
(4, 'Pergaminho Mágico', 'Um pergaminho que pode revelar pistas importantes.', TRUE),
(5, 'Artefato Lendário', 'Um artefato antigo que brilha com uma luz mágica.', FALSE),
(5, 'Enigma', 'Um enigma gravado na parede que deve ser resolvido para acessar o artefato.', TRUE);


INSERT INTO commands (command_name, description) VALUES
('HELP', 'Exibe este menu de ajuda.'),
('USE [ITEM]', 'Interage com o item especificado na cena.'),
('CHECK [ITEM]', 'Mostra a descrição do item especificado na cena.'),
('GET [ITEM]', 'Adiciona o item especificado ao seu inventário.'),
('INVENTORY', 'Mostra os itens que você tem no inventário.'),
('USE [INVENTORY_ITEM] WITH [SCENE_ITEM]', 'Usa um item do inventário com um item da cena.'),
('SAVE', 'Salva o progresso atual do jogo.'),
('LOAD', 'Carrega um jogo salvo.'),
('RESTART', 'Reinicia o jogo.');


show tables;