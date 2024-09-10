package service;

import model.Console;
import model.Save;
import repository.SaveDAO;

import java.util.ArrayList;
import java.util.List;

public class ComandoStart {
    private Console console;
    private List<Console> listaConsole;

    public ComandoStart() {
        this.console = new Console();
        this.listaConsole = new ArrayList<>();
    }

    public List<Console> executar() {
        try {
            Save save = SaveDAO.novoJogo();
            
            if (save != null && save.getCenaAtual() != null) {
                console.setMensagem(save.getCenaAtual().getDescricao());
                console.setIdSave(save.getIdSave());
                listaConsole.add(console);
            } else {
                console.setMensagem("Não foi possível iniciar o jogo.");
                listaConsole.add(console);
            }
        } catch (Exception e) {
            console.setMensagem("Erro ao iniciar o jogo: " + e.getMessage());
            e.printStackTrace();
            listaConsole.add(console);
        }
        
        return listaConsole;
    }
}
