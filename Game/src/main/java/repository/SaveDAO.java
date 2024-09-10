package repository;

import model.Save;
import model.Cena;
import java.sql.*;

public class SaveDAO {

    public static Save novoJogo() throws SQLException {
        String sql = "INSERT INTO saves(id_cena_atual) VALUES (1)";
        Save save = new Save();

        try (Connection conn = Mysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.executeUpdate();
            
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    save.setIdSave(generatedKeys.getInt(1));
                    
                    Cena cenaAtual = CenaDAO.findCenaById(1);
                    if (cenaAtual != null) {
                        save.setCenaAtual(cenaAtual);
                    } else {
                        throw new SQLException("Cena com ID 1 não encontrada.");
                    }
                } else {
                    throw new SQLException("Falha ao obter a chave gerada.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e; 
        }

        return save;
    }
}
