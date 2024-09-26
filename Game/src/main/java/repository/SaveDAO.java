package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SaveDAO {
    private Connection connection;

    public SaveDAO(Connection connection) {
        this.connection = connection;
    }

    public int criarNovoSave(int idJogador) throws SQLException {
        String sql = "INSERT INTO saves (id_jogador) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, idJogador);
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Retorna o id gerado
                } else {
                    throw new SQLException("Falha ao obter o ID do save.");
                }
            }
        }
    }
}
