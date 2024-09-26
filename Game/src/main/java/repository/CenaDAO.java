package repository;

import model.Cena;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CenaDAO {
    private final Connection connection;

    public CenaDAO(Connection connection) {
        this.connection = connection;
    }

    public Cena getCenaPorId(Integer id) throws SQLException {
        Cena cena = null;
        String sql = "SELECT * FROM cenas WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cena = new Cena(
                    rs.getInt("id"),
                    rs.getString("descricao")
                );
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar Cena com ID " + id + ": " + e.getMessage(), e);
        }
        
        return cena;
    }

    public int insertCena(Cena cena) throws SQLException {
        String insert = "INSERT INTO cenas(descricao) VALUES (?);"; // Somente a coluna descrição
        int generatedId = -1;

        try (PreparedStatement ps = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, cena.getDescricao());
            ps.executeUpdate();

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir Cena: " + e.getMessage(), e);
        }
        
        return generatedId;
    }
    
    public List<Cena> findAll() throws SQLException {
        String sql = "SELECT * FROM cenas;";
        List<Cena> cenas = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery()) {

            while (resultSet.next()) {
                Cena cena = new Cena(
                    resultSet.getInt("id"),
                    resultSet.getString("descricao")
                );
                cenas.add(cena);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar todas as Cenas: " + e.getMessage(), e);
        }
        
        return cenas;
    }

    public Cena getCenaInicial() throws SQLException {
        return getCenaPorId(1);
    }
}
