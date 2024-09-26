package controller;

import model.Save;
import service.ComandoService;

public class DuranteOJogoController {
    private final ComandoService comandoService;
    private final Save save;

    public DuranteOJogoController(ComandoService comandoService, Save save) {
        this.comandoService = comandoService;
        this.save = save;
    }

    public String processarComando(String comando) {
        return comandoService.processarComando(comando, save);
    }

    public String salvarProgresso() {
        return "Progresso salvo com sucesso!";
    }

    public String lookAoRedor() {
        return "Você olha ao redor e vê...";
    }

    public String usarItem(String itemNome) {
        return comandoService.usarItem(save, itemNome);
    }
    
    public String pegarItem(String itemNome) {
        return comandoService.pegarItem(save, itemNome);
    }
}
