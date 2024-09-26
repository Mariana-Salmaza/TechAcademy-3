package repository;

import model.Save;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SaveDAO {
    private final Connection connection;

    public SaveDAO(Connection connection) {
        this.connection = connection;
    }

    public int adicionarSave(Save save) throws SQLException {
        String sql = "INSERT INTO save (id_cena_atual) VALUES (?)";
        int generatedId = -1;

        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, save.getIdCenaAtual());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao adicionar save: " + e.getMessage(), e);
        }

        return generatedId;
    }

    
    public Save buscarSavePorId(int id) throws SQLException {
        String sql = "SELECT * FROM save WHERE id = ?";
        Save save = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    save = new Save();
                    save.setId(rs.getInt("id"));
                    save.setIdCenaAtual(rs.getInt("id_cena_atual"));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar save com ID " + id + ": " + e.getMessage(), e);
        }

        return save;
    }

  
    public List<Save> listarSaves() throws SQLException {
        List<Save> saves = new ArrayList<>();
        String sql = "SELECT * FROM save";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Save save = new Save();
                save.setId(rs.getInt("id"));
                save.setIdCenaAtual(rs.getInt("id_cena_atual"));
                saves.add(save);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar saves: " + e.getMessage(), e);
        }

        return saves;
    }

   
    public void removerSave(int id) throws SQLException {
        String sql = "DELETE FROM save WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao remover save com ID " + id + ": " + e.getMessage(), e);
        }
    }
}
