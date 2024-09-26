<?php

//session_start();

if (isset($_GET['comando'])) {
    $comando = rawurlencode($_GET['comando']);
    $save = isset($_GET['save']) ? rawurlencode($_GET['save']) : '';
    
    // Montar a URL de acordo com a presença de 'save'
    $url = "http://localhost:4567/{$comando}" . ($save ? "/{$save}" : '');
    
    // Obter o conteúdo da URL
    $conteudo = @file_get_contents($url);
    
    // Verificar se o conteúdo foi recuperado com sucesso
    if ($conteudo === FALSE) {
        
        $arrayAssociativo = ['mensagem' => 'Erro ao conectar ao servidor.'];
    } else {
        $arrayAssociativo = json_decode($conteudo, true); 
    }
} else {
    $conteudo = @file_get_contents("http://localhost:4567");
    $arrayAssociativo = json_decode($conteudo, true); 
}

include "template.phtml";
