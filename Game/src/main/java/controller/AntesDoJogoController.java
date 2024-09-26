package controller;

import service.ComandoService;

public class AntesDoJogoController {
    private final ComandoService comandoService;

    public AntesDoJogoController(ComandoService comandoService) {
        this.comandoService = comandoService;
    }

    public String iniciarJogo(String comando) {
        if (comando.equalsIgnoreCase("start")) {
            return "Jogo iniciado. Você pode começar sua aventura!";
        }
        return "Comando inválido. Digite 'start' para iniciar o jogo.";
    }
}
