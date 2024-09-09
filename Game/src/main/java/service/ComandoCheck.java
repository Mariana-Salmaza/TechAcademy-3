package service;

import model.Console;
import model.Item;
import repository.ItemDAO;

import java.sql.SQLException;

public class ComandoCheck {
    private Console console;

    public ComandoCheck() {
        this.console = new Console();
    }

    public Console executar(String nomeItem) {
        try {
            
            Item item = ItemDAO.findItemByName(nomeItem);
            
            if (item == null) {
    
                console.setMensagem("O item " + nomeItem + " não foi encontrado.");
                return console;
            }

            console.setMensagem("Item: " + item.getNome() + "\nDescrição: " + item.getDescricao());

        } catch (SQLException e) {
            
            console.setMensagem("Erro ao processar o comando: " + e.getMessage());
        }

        return console;
    }
}
