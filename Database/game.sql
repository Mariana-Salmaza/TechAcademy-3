CREATE DATABASE game;
USE game;

CREATE TABLE IF NOT EXISTS cenas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao TEXT
);

CREATE TABLE IF NOT EXISTS progresso_do_jogador (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_jogador VARCHAR(255) NOT NULL,
    id_cena_atual INT,
    FOREIGN KEY (id_cena_atual) REFERENCES cenas(id)
);

CREATE TABLE IF NOT EXISTS itens_da_cena (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cena_atual INT,
    id_cena_destino INT,
    nome VARCHAR(255),
    descricao_positiva TEXT,
    descricao_negativa TEXT,
    comando_correto VARCHAR(255),
    interagivel BOOLEAN,
    FOREIGN KEY (id_cena_atual) REFERENCES cenas(id),
    FOREIGN KEY (id_cena_destino) REFERENCES cenas(id)
);

CREATE TABLE IF NOT EXISTS itens_de_inventario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_jogador VARCHAR(255),
    nome VARCHAR(255) NOT NULL,
    descricao TEXT,
    id_jogador INT,
    FOREIGN KEY (id_jogador) REFERENCES progresso_do_jogador(id)
);

CREATE TABLE IF NOT EXISTS comandos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome_comando VARCHAR(255) NOT NULL,
    descricao TEXT NOT NULL
);

INSERT INTO cenas (descricao) VALUES
('Você está na sala de entrada de um velho castelo. O lugar está empoeirado e mal iluminado. Há uma porta ao norte e uma escada que desce para o porão. No canto da sala, há uma LANTERNA e uma CHAVE DE FERRO em uma mesa antiga. Use a LANTERNA para explorar o escuro corredor ou a CHAVE DE FERRO para tentar abrir um baú trancado.'),
('O corredor está sombrio e o vento parece sussurrar segredos antigos. As paredes estão cobertas de tapeçarias rasgadas e há um odor de mofo no ar. Há portas a leste e a oeste. Um DIÁRIO DO CONDE está em uma mesa próxima, e um MAPA DO CASTELO está pendurado na parede. O DIÁRIO pode conter dicas para desvendar o enigma, e o MAPA pode revelar caminhos secretos.'),
('Esta sala está repleta de ouro, joias e itens antigos. No centro, há um pedestal com uma CAIXA DE OURO trancada e uma ESPADA DE PRATA ao lado. A CAIXA DE OURO pode conter um item valioso, e a ESPADA DE PRATA pode ser útil para futuros desafios.'),
('A biblioteca secreta é cheia de livros e pergaminhos antigos. A iluminação é fraca e há uma sensação de mistério no ar. Há uma mesa com um LIVRO DE FEITIÇOS e uma escada que leva a um compartimento secreto onde um PERGAMINHO MÁGICO está escondido. O LIVRO DE FEITIÇOS pode conter feitiços para resolver o enigma, e o PERGAMINHO MÁGICO pode fornecer pistas adicionais.'),
('Você entra em uma câmara misteriosa com um pedestal no centro. Em cima do pedestal, há um ARTEFATO LENDÁRIO envolto em uma aura mágica. A sala está protegida por um ENIGMA gravado na parede que você precisa resolver para pegar o artefato. Use todos os itens coletados para resolver o enigma e acessar o ARTEFATO LENDÁRIO.');

INSERT INTO itens_da_cena (id_cena_atual, id_cena_destino, nome, descricao_positiva, descricao_negativa, comando_correto, interagivel) VALUES
(1, 2, 'LANTERNA', 'Uma lanterna antiga, ainda com um pouco de óleo. Pode ajudar a iluminar o caminho em áreas escuras do castelo.', NULL, 'USE LANTERNA', 1),
(1, NULL, 'CHAVE DE FERRO', 'Uma pequena CHAVE enferrujada, aparentemente usada para trancar algo.', 'Essa chave não parece servir para nada aqui.', 'USE CHAVE DE FERRO', 1),
(2, 3, 'DIÁRIO DO CONDE', 'Um velho DIÁRIO que fala sobre os segredos do castelo.', 'O diário não tem informações úteis neste momento.', 'USE DIÁRIO DO CONDE', 1),
(2, 3, 'MAPA DO CASTELO', 'Um MAPA antigo que pode ajudar a explorar o castelo.', 'O mapa não revela nada novo agora.', 'USE MAPA DO CASTELO', 1),
(3, 4, 'CAIXA DE OURO', 'Uma CAIXA trancada com um mecanismo complicado. Pode conter um item valioso.', 'A caixa está trancada e você não tem a chave certa para abri-la.', 'USE CAIXA DE OURO', 1),
(3, 4, 'ESPADA DE PRATA', 'Uma ESPADA antiga com inscrições mágicas. Pode ser útil em um confronto futuro.', 'A espada não tem utilidade imediata agora.', 'USE ESPADA DE PRATA', 1),
(4, 5, 'LIVRO DE FEITIÇOS', 'Um LIVRO que contém feitiços antigos e rituais. Pode ajudar a resolver o ENIGMA.', 'O livro não ajuda diretamente com o enigma agora.', 'USE LIVRO DE FEITIÇOS', 1),
(4, 5, 'PERGAMINHO MÁGICO', 'Um PERGAMINHO que pode revelar pistas importantes para resolver o enigma da câmara.', 'O pergaminho não é necessário agora.', 'USE PERGAMINHO MÁGICO', 1),
(5, NULL, 'ARTEFATO LENDÁRIO', 'Um ARTEFATO antigo que brilha com uma luz mágica. É o objetivo final do jogo.', 'Você não pode pegar o artefato sem resolver o enigma primeiro.', 'USE ARTEFATO LENDÁRIO', 0),
(5, NULL, 'ENIGMA', 'Um ENIGMA gravado na parede que deve ser resolvido para acessar o artefato.', 'Você não pode resolver o enigma sem pistas.', 'RESOLVA ENIGMA', 1);

INSERT INTO comandos (nome_comando, descricao) VALUES
('AJUDA', 'Exibe este menu de ajuda.'),
('USAR [ITEM]', 'Interage com o item especificado na cena.'),
('VERIFICAR [ITEM]', 'Mostra a descrição do item especificado na cena.'),
('PEGAR [ITEM]', 'Adiciona o item especificado ao seu inventário.'),
('INVENTÁRIO', 'Mostra os itens que você tem no inventário.'),
('USAR [ITEM_DO_INVENTARIO] COM [ITEM_DA_CENA]', 'Usa um item do inventário com um item da cena.'),
('SALVAR', 'Salva o progresso atual do jogo.'),
('CARREGAR', 'Carrega um jogo salvo.'),
('REINICIAR', 'Reinicia o jogo.');

SHOW TABLES;

SELECT * FROM itens_da_cena WHERE id_cena_atual = 1;
