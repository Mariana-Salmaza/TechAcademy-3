<?php

session_start();

if(isset($_GET['comando']) && isset($_GET['save']) && $_GET['save']) {
    $comando = rawurlencode($_GET['comando']);
    $save = rawurlencode($_GET['save']);
    $conteudo = file_get_contents("htpp://localhost:4567/{$comando}/{$save}");
} else if(isset($_GET['comando'])) {
    $comando = rawurlencode($_GET['comando']);
    $conteudo = file_get_contents("http://localhost:4567/{$comando}");
}else {
    $conteudo = file_get_contents("http://localhost:4567");
}

$arrayAssociativo = json_decode($conteudo);
$_SESSION['historico'] = isset($_SESSION['historico']) ? array_merge($_SESSION['historico'], $arrayAssociativo) : [];

$arrayAssociativo = $_SESSION['historico'];

include "templete.phtml";