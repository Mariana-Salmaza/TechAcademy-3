package service;

import model.Console;
import model.Inventario;
import model.Item;
import repository.InventarioDAO;
import repository.ItemDAO;

import java.sql.SQLException;
import java.util.List;

public class ComandoUseWith {
    private Console console;

    public ComandoUseWith() {
        this.console = new Console();
    }

    public Console executar(String itemInventarioNome, String itemCenaNome) {
        try {
            
            List<Inventario> inventarios = InventarioDAO.findInventariosByNomeItem(itemInventarioNome);
            if (inventarios.isEmpty()) {
                console.setMensagem("O item " + itemInventarioNome + " não está no seu inventário.");
                return console;
            }

            
            Item itemCena = ItemDAO.findItemByName(itemCenaNome);
            if (itemCena == null) {
                console.setMensagem("O item da cena " + itemCenaNome + " não foi encontrado.");
                return console;
            }

            String comandoCorreto = "USE " + itemInventarioNome + " WITH " + itemCenaNome;
            if (!itemCena.getComandoCorreto().equalsIgnoreCase(comandoCorreto)) {
                console.setMensagem("Você não pode usar " + itemInventarioNome + " com " + itemCenaNome + " aqui.");
                return console;
            }

           
            console.setMensagem("Você usou " + itemInventarioNome + " com " + itemCenaNome + ". " + itemCena.getDescricaoPositiva());

        } catch (SQLException e) {
            console.setMensagem("Erro ao processar o comando: " + e.getMessage());
            e.printStackTrace(); 
        }

        return console;
    }
}
