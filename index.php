

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Midnigth Castle</title>
    <style>
        body {
            background-color: #000;
            color: #0f0;
            font-family: 'Courier New', Courier, monospace;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }
        .logo, .img {
            margin-bottom: 20px;
            
        
        }
            
        
        
        .container {
            width: 60%;
            max-width: 800px;
            padding: 20px;
            border: 1px solid #0f0;
            border-radius: 5px;
            background-color: #000;
            box-shadow: 0 0 10px #0f0;
        }
        .history, .response {
            width: 100%;
            margin-bottom: 20px;
            padding: 10px;
            border: 1px solid #0f0;
            background-color: #000;
            color: #0f0;
            font-family: 'Courier New', Courier, monospace;
            box-sizing: border-box;
            border-radius: 5px;
        }
        .response {
            height: 40px;
        }
        .submit-btn {
            background-color:#000;
            color:  #0f0;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            font-family: 'Courier New', Courier, monospace;
            border-radius: 5px;
            font-size: 16px;
            display: flex;
            align-items: center;
            justify-content: center;
            border: 1px solid #0f0;
        }
        .submit-btn:before {
            content: '→';
            margin-right: 8px;
        }
        .help {
            margin-top: 20px;
            padding: 20px;
            border: 1px solid #0f0;
            background-color: #000;
            border-radius: 5px;
            font-size: 14px;
            white-space: pre-wrap;
            text-align: left;
        }
    </style>
</head>
<body>
<?php

$perguntas = [
    "" => "",
    "" => "",
    "" => "",
];

$respostas = [];
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    foreach ($perguntas as $pergunta => $campo) {
        $respostas[$campo] = htmlspecialchars($_POST[$campo] ?? '');
    }
}
?>
    <div class="logo">
        <img src="img/logo3.png">
        <h1></h1>
    </div>
    <div class="container">x    
        <form method="post">
            <textarea class="history" readonly>
             história...
            </textarea>
            <input type="text" class="response" name="resposta" value="<?= $respostas['resposta'] ?? '' ?>">
            <button type="submit" class="submit-btn"></button>
        </form>
    </div>
    <div class="help">
<strong>•HELP:</strong> Exibe o menu de ajuda do jogo

<strong>•USE [ITEM]:</strong> interage com o item da cena 

<strong>•CHECK [ITEM]:</strong> mostra a descrição do objeto na cena

<strong>•GET [ITEM]:</strong> Se possível, adiciona o item ao inventário 

<strong>•INVENTORY:</strong> mostra os itens que estão no inventário

<strong>•USE [INVENTORY_ITEM] WITH [SCENE_ITEM]:</strong>
Realiza a ação utilizando um item do inventário com 
um item da cena 

<strong>•SAVE: salva o jogo</strong> salva o jogo 

<strong>•LOAD:</strong> carrega um jogo salvo 

<strong>•RESTART:</strong> reinicia o jogo 
    </div>
</body>
</html>
