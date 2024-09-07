package repository;

import model.ProgressoJogo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgressoJogoDAO {
    private Connection connection;

    public ProgressoJogoDAO(Connection connection) {
        this.connection = connection;
    }

    public void saveProgresso(ProgressoJogo progresso) throws SQLException {
        String sql = "INSERT INTO progresso_jogo (id_save, cena_atual, cena_destino) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, progresso.getIdSave());
            stmt.setInt(2, progresso.getCenaAtual());
            stmt.setInt(3, progresso.getCenaDestino());
            stmt.executeUpdate();
        }
    }

    public ProgressoJogo getProgresso(int idSave) throws SQLException {
        String sql = "SELECT * FROM progresso_jogo WHERE id_save = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idSave);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ProgressoJogo(
                        rs.getInt("id"),
                        rs.getInt("id_save"),
                        rs.getInt("cena_atual"),
                        rs.getInt("cena_destino")
                    );
                }
            }
        }
        return null;
    }
}
