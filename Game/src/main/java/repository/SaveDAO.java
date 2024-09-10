package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Save;

public class SaveDAO {

    public static void resetSaveToInitialState(int idSave) throws SQLException {
        Connection connection = Mysql.getConnection();
        String query = "UPDATE save SET id_cena_atual = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
        
            int cenaInicialId = 1;
            statement.setInt(1, cenaInicialId);
            statement.setInt(2, idSave);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Nenhuma linha atualizada, talvez o ID do save esteja incorreto.");
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
            throw e; 
        }
    }

    public static Save findSaveById(int idSave) {
        throw new UnsupportedOperationException("Unimplemented method 'findSaveById'");
    }
    public static Save novoJogo() {
        throw new UnsupportedOperationException("Unimplemented method 'novoJogo'");
    }
}
