CREATE DATABASE game;
USE game;

CREATE TABLE IF NOT EXISTS cenas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS itens_da_cena (
    id_item INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao_positiva TEXT,
    descricao_negativa TEXT,
    comando_correto VARCHAR(100),
    id_cena_atual INT,
    id_cena_destino INT,
    interagivel BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (id_cena_atual) REFERENCES cenas(id),
    FOREIGN KEY (id_cena_destino) REFERENCES cenas(id)
);

CREATE TABLE IF NOT EXISTS itens_inventario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao_positiva TEXT,
    descricao_negativa TEXT,
    id_save INT NOT NULL
);

CREATE TABLE IF NOT EXISTS save (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_jogador INT NOT NULL,
    id_cena_atual INT,
    FOREIGN KEY (id_cena_atual) REFERENCES cenas(id)
);

CREATE TABLE IF NOT EXISTS use_with (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_item_inventario INT NOT NULL,
    id_item_cena INT NOT NULL,
    descricao_acao TEXT,
    FOREIGN KEY (id_item_inventario) REFERENCES itens_inventario(id) ON DELETE CASCADE,
    FOREIGN KEY (id_item_cena) REFERENCES itens_da_cena(id_item) ON DELETE CASCADE
);


INSERT INTO cenas (descricao) VALUES
('Você está na sala de entrada de um velho castelo. O lugar está empoeirado e mal iluminado. Há uma porta ao norte e uma escada que desce para o porão. No canto da sala, há uma LANTERNA e uma CHAVE DE FERRO em uma mesa antiga. Você pode usar a LANTERNA para iluminar o caminho e explorar o corredor escuro ao norte ou usar a CHAVE DE FERRO para tentar abrir um baú trancado na sala abaixo. Sua escolha pode revelar novos caminhos ou itens úteis para sua jornada.'),
('O corredor está sombrio e o vento parece sussurrar segredos antigos. As paredes estão cobertas de tapeçarias rasgadas e há um odor de mofo no ar. Há portas a leste e a oeste. Se você escolheu explorar o corredor ao norte, encontrará um DIÁRIO DO CONDE em uma mesa próxima, e um MAPA DO CASTELO pendurado na parede. O DIÁRIO pode conter dicas para desvendar enigmas, enquanto o MAPA pode revelar caminhos secretos. Escolha seu próximo caminho para continuar sua exploração.'),
('Esta sala está repleta de ouro, joias e itens antigos. No centro, há um pedestal com uma CAIXA DE OURO trancada e uma ESPADA DE PRATA ao lado. Se você conseguiu usar o DIÁRIO DO CONDE e o MAPA DO CASTELO, pode agora abrir a CAIXA DE OURO para descobrir um item valioso, enquanto a ESPADA DE PRATA pode ser útil para futuros desafios. O próximo passo é decidir como você utilizará os itens que encontrou.'),
('A biblioteca secreta é cheia de livros e pergaminhos antigos. A iluminação é fraca e há uma sensação de mistério no ar. Se você utilizou a ESPADA DE PRATA ou a CAIXA DE OURO, encontrará uma mesa com um LIVRO DE FEITIÇOS e uma escada que leva a um compartimento secreto onde um PERGAMINHO MÁGICO está escondido. O LIVRO DE FEITIÇOS pode conter feitiços para resolver o ENIGMA, enquanto o PERGAMINHO MÁGICO pode fornecer pistas adicionais para avançar.'),
('Você entra em uma câmara misteriosa com um pedestal no centro. Em cima do pedestal, há um ARTEFATO LENDÁRIO envolto em uma aura mágica. A sala está protegida por um ENIGMA gravado na parede que você precisa resolver para pegar o artefato. Use todos os itens coletados e as pistas obtidas para resolver o enigma e acessar o ARTEFATO LENDÁRIO. Sua habilidade de resolver enigmas e utilizar os itens adquiridos será crucial para alcançar o objetivo final.'),
('Parabéns! Você resolveu o enigma e obteve o ARTEFATO LENDÁRIO! Sua jornada pelo castelo chegou ao fim, e os segredos antigos agora são seus. Que novas aventuras aguardam você?');


INSERT INTO itens_da_cena (nome, descricao_positiva, descricao_negativa, comando_correto, id_cena_atual, id_cena_destino, interagivel) VALUES
('LANTERNA', 'Uma lanterna antiga, ainda com um pouco de óleo. Pode ajudar a iluminar o caminho em áreas escuras do castelo.', 'A lanterna não serve para nada aqui.', 'USE LANTERNA', 1, NULL, 1),
('CHAVE DE FERRO', 'Uma pequena CHAVE enferrujada, aparentemente usada para trancar algo.', 'Essa chave não parece servir para nada aqui.', 'USE CHAVE DE FERRO', 1, NULL, 1),
('DIÁRIO DO CONDE', 'Um velho DIÁRIO que fala sobre os segredos do castelo.', 'O diário não tem informações úteis neste momento.', 'USE DIÁRIO DO CONDE', 2, NULL, 1),
('MAPA DO CASTELO', 'Um MAPA antigo que pode ajudar a explorar o castelo.', 'O mapa não revela nada novo agora.', 'USE MAPA DO CASTELO', 2, NULL, 1),
('CAIXA DE OURO', 'Uma CAIXA trancada com um mecanismo complicado. Pode conter um item valioso.', 'A caixa está trancada e você não tem a chave certa para abri-la.', 'USE CAIXA DE OURO', 3, NULL, 1),
('ESPADA DE PRATA', 'Uma ESPADA antiga com inscrições mágicas. Pode ser útil em um confronto futuro.', 'A espada não tem utilidade imediata agora.', 'USE ESPADA DE PRATA', 3, NULL, 1),
('LIVRO DE FEITIÇOS', 'Um LIVRO que contém feitiços antigos e rituais. Pode ajudar a resolver o ENIGMA.', 'O livro não ajuda diretamente com o enigma agora.', 'USE LIVRO DE FEITIÇOS', 4, NULL, 1),
('PERGAMINHO MÁGICO', 'Um PERGAMINHO que pode revelar pistas importantes para resolver o enigma da câmara.', 'O pergaminho não é necessário agora.', 'USE PERGAMINHO MÁGICO', 4, NULL, 1),
('ARTEFATO LENDÁRIO', 'Um ARTEFATO antigo que brilha com uma luz mágica. É o objetivo final do jogo.', 'Você não pode pegar o artefato sem resolver o enigma primeiro.', 'USE ARTEFATO LENDÁRIO', 5, NULL, 0),
('ENIGMA', 'Um ENIGMA gravado na parede que deve ser resolvido para acessar o artefato.', 'Você não pode resolver o enigma sem pistas.', 'RESOLVA ENIGMA', 5, NULL, 1),
('FINAL DO JOGO', 'Você resolveu o enigma e obteve o ARTEFATO LENDÁRIO! Sua jornada pelo castelo chegou ao fim, e os segredos antigos agora são seus. Que novas aventuras aguardam você?', 'Não há nada a fazer aqui.', 'VEJA FINAL', 6, NULL, 0);


SELECT 
    ic.nome,
    ic.descricao_positiva
FROM 
    itens_da_cena ic
INNER JOIN 
    use_with uw ON ic.id_item = uw.id_item_cena
INNER JOIN 
    itens_inventario ii ON uw.id_item_inventario = ii.id
WHERE 
    ic.id_item = 11; 
   
