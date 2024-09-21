package service;

import model.Console;
import model.Save;
import model.UseWith;
import repository.SaveDAO;
import repository.UseWithDAO;

import java.util.List;

public class ComandoService {
    private final String[] comando;
    private final Console console;

    public ComandoService(String comandoBruto) {
        this.console = new Console();
        this.comando = comandoBruto.split(" ");
    }

    private Console help() {
        console.setMensagem("Este aqui é o texto de ajuda");
        return console;
    }

    private Console start() {
        try {
            Save save = SaveDAO.novoJogo();
            console.setMensagem(save.getCenaAtual().getDescricao());
            console.setIdSave(save.getIdSave());
            return console;
        } catch (Exception e) {
            e.printStackTrace();
            console.setMensagem("Erro ao tentar iniciar o jogo");
            return console;
        }
    }

    private Console useWith() {
        if (comando.length < 3) {
            console.setMensagem("Comando usewith inválido. Use o formato: usewith <id_item_inventario> <id_item_cena>");
            return console;
        }

        try {
            int idItemInventario = Integer.parseInt(comando[1]);
            int idItemCena = Integer.parseInt(comando[2]);

            List<UseWith> actions = UseWithDAO.findActionsByItemId(idItemInventario);

            boolean actionFound = false;
            for (UseWith action : actions) {
                if (action.getIdItemCena() == idItemCena) {
                    console.setMensagem(action.getDescricaoAcao());
                    actionFound = true;
                    break;
                }
            }

            if (!actionFound) {
                console.setMensagem("Nenhuma ação encontrada para os itens fornecidos.");
            }

            return console;
        } catch (NumberFormatException e) {
            console.setMensagem("IDs inválidos fornecidos.");
            return console;
        } catch (Exception e) {
            e.printStackTrace();
            console.setMensagem("Erro ao processar comando usewith");
            return console;
        }
    }

    public Console getResultadoConsole() {
        try {
            String primeiroComando = comando[0].toLowerCase();

            switch (primeiroComando) {
                case "help":
                    return help();
                case "start":
                    return start();
                case "usewith":
                    return useWith();
                default:
                    console.setMensagem("Comando inválido");
                    return console;
            }
        } catch (Exception e) {
            console.setMensagem("Erro ao processar comando");
            return console;
        }
    }
}
