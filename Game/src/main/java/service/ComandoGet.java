package service;

import model.Console;
import model.Item;
import repository.ItemDAO;

public class ComandoGet {
    private Console console;

    public ComandoGet() {
        this.console = new Console();
    }

    public Console executar(String itemNome) {
        try {
            Item item = ItemDAO.findItemByName(itemNome);
            if (item != null) {
                console.setMensagem("Item encontrado: Nome - " + item.getNome() + ", Descrição - " + item.getDescricao());
            } else {
                console.setMensagem("Item não encontrado.");
            }
        } catch (Exception e) {
            console.setMensagem("Erro ao buscar o item: " + e.getMessage());
        }
        return console;
    }
}
