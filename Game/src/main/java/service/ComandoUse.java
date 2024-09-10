package service;

import model.Console;
import model.Inventario;
import model.Item;
import repository.InventarioDAO;
import repository.ItemDAO;

import java.sql.SQLException;
import java.util.List;

public class ComandoUse {
    private Console console;

    public ComandoUse() {
        this.console = new Console();
    }

    public Console executar(String itemInventarioNome, String itemCenaNome) {
        try {
            int idSave = console.getIdSave(); 

            List<Inventario> inventarios = InventarioDAO.findInventariosBySaveId(idSave);

            boolean itemEncontradoNoInventario = false;
            for (Inventario inventario : inventarios) {
                if (inventario.getNomeItem().equals(itemInventarioNome)) {
                    itemEncontradoNoInventario = true;
                    break;
                }
            }

            if (!itemEncontradoNoInventario) {
                console.setMensagem("O item " + itemInventarioNome + " não está no seu inventário.");
                return console;
            }

            Item itemCena = ItemDAO.findItemByName(itemCenaNome);
            if (itemCena == null) {
                console.setMensagem("O item da cena " + itemCenaNome + " não foi encontrado.");
                return console;
            }

            if (!itemCena.getComandoCorreto().equalsIgnoreCase("USE " + itemInventarioNome)) {
                console.setMensagem("Você não pode usar " + itemInventarioNome + " com " + itemCenaNome + " aqui.");
                return console;
            }

            console.setMensagem("Você usou " + itemInventarioNome + " com " + itemCenaNome + ". " + itemCena.getDescricaoPositiva());

        } catch (SQLException e) {
            console.setMensagem("Erro ao processar o comando: " + e.getMessage());
        }

        return console;
    }
}
