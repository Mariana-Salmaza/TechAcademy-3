<?php
session_start();


function fetch_content($url) {
    $response = @file_get_contents($url);
    if ($response === FALSE) {
        return null; 
    }
    return $response;
}

$conteudo = null;
if (isset($_GET['comando'])) {
    $comando = rawurlencode($_GET['comando']);
    $url = "http://localhost:4567/{$comando}";

    if (isset($_GET['save']) && $_GET['save']) {
        $save = rawurlencode($_GET['save']);
        $url .= "/{$save}";
    }

    $conteudo = fetch_content($url);
} else {
    $conteudo = fetch_content("http://localhost:4567");
}


if ($conteudo) {
    $arrayAssociativo = json_decode($conteudo, true); 
    if (json_last_error() === JSON_ERROR_NONE) {
      
        $_SESSION['historico'] = isset($_SESSION['historico']) && is_array($_SESSION['historico'])
            ? array_merge($_SESSION['historico'], $arrayAssociativo)
            : $arrayAssociativo;
    } else {
        
        $_SESSION['historico'] = isset($_SESSION['historico']) ? $_SESSION['historico'] : [];
    }
} else {
 
    $_SESSION['historico'] = isset($_SESSION['historico']) ? $_SESSION['historico'] : [];
}

$arrayAssociativo = $_SESSION['historico'] ?? [];
$respostas = [];
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    foreach ($_POST as $key => $value) {
        $respostas[$key] = htmlspecialchars($value, ENT_QUOTES, 'UTF-8');
    }
}
?>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Midnight Castle</title>
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
        .console-box {
            width: 60%;
            max-width: 800px;
            margin-bottom: 20px;
            padding: 20px;
            border: 1px solid #0f0;
            border-radius: 5px;
            background-color: #000;
            box-shadow: 0 0 10px #0f0;
        }
        .console-box p {
            background-color: #222;
            color: #0f0;
            padding: 10px;
            border-radius: 5px;
            margin: 5px 0;
            white-space: pre-wrap;
        }
        .form-group {
            width: 60%;
            max-width: 800px;
            margin-bottom: 20px;
        }
        .form-group input[type="text"] {
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #0f0;
            background-color: #000;
            color: #0f0;
            border-radius: 5px;
            font-size: 16px;
            box-sizing: border-box;
        }
        .form-group input[type="submit"] {
            background-color: #000;
            color: #0f0;
            border: 1px solid #0f0;
            padding: 10px;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .form-group input[type="submit"]:hover {
            background-color: #0c0;
        }
    </style>
</head>
<body>
    <main>
        <div class="console-box">
            <?php if (isset($arrayAssociativo['messages']) && is_array($arrayAssociativo['messages'])): ?>
                <?php foreach($arrayAssociativo['messages'] as $mensagem): ?>
                    <p><?= htmlspecialchars($mensagem, ENT_QUOTES, 'UTF-8') ?></p>
                <?php endforeach ?>
            <?php else: ?>
                <p>No messages available.</p>
            <?php endif ?>
        </div>
        <div class="form-group">
            <form method="get">
                <input type="text" name="comando" placeholder="Enter command" required />
                <input type="text" name="save" value="<?= htmlspecialchars($arrayAssociativo['idGame'] ?? '', ENT_QUOTES, 'UTF-8') ?>" readonly />
                <input type="submit" value="Submit" />
            </form>
        </div>
    </main>
    <div class="logo">
        <img src="img/logo3.png" alt="Logo">
        <h1></h1>
    </div>
    <div class="container">
        <form method="post">
            <textarea class="history" readonly>
                história...
            </textarea>
            <input type="text" class="response" name="resposta" value="<?= htmlspecialchars($respostas['resposta'] ?? '', ENT_QUOTES, 'UTF-8') ?>">
            <button type="submit" class="submit-btn">Enviar</button>
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

        <strong>•SAVE:</strong> salva o jogo

        <strong>•LOAD:</strong> carrega um jogo salvo 

        <strong>•RESTART:</strong> reinicia o jogo 
    </div>
</body>
</html>
