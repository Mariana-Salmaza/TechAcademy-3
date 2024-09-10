package service;

import model.Console;
import model.Inventario;
import model.Save;
import repository.SaveDAO;
import repository.InventarioDAO;

import java.sql.SQLException;
import java.util.List;

public class ComandoLoad {
    private Console console;

    public ComandoLoad() {
        this.console = new Console();
    }

    public Console executar(int idSave) {
        try {
            
            Save save = SaveDAO.findSaveById(idSave);

            if (save != null) {
              
                List<Inventario> inventarios = InventarioDAO.findInventariosBySaveId(idSave);
                
                console.setMensagem("Jogo carregado com sucesso. ID do Save: " + save.getIdSave() +
                                    "\nItens no inventário: " + inventarios.size());
                console.setIdSave(save.getIdSave());

            } else {
                console.setMensagem("Não foi possível encontrar o jogo com ID: " + idSave);
            }
        } catch (SQLException e) {
            console.setMensagem("Erro ao carregar o jogo: " + e.getMessage());
            e.printStackTrace(); 
        }
        return console;
    }
}
