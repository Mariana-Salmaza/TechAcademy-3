package service;

import model.Console;
import model.Save;
import repository.SaveDAO;
import repository.InventarioDAO;
import java.sql.SQLException;

public class ComandoRestart {
    private Console console;

    public ComandoRestart() {
        this.console = new Console();
    }

    public Console executar(int idSave) {
        try {
            Save save = SaveDAO.findSaveById(idSave);

            if (save != null) {
        
                reiniciarEstadoInicial(save);
                console.setMensagem("O jogo foi reiniciado com sucesso. ID do Save: " + save.getIdSave());
            } else {
                console.setMensagem("Não foi possível encontrar o jogo para reiniciar.");
            }
        } catch (SQLException e) {
            
            console.setMensagem("Erro ao reiniciar o jogo: " + e.getMessage());
            e.printStackTrace(); 
        }
        return console;
    }

    private void reiniciarEstadoInicial(Save save) throws SQLException {
       
        InventarioDAO.clearInventarioBySaveId(save.getIdSave());
    
        SaveDAO.resetSaveToInitialState(save.getIdSave());
    }
}
