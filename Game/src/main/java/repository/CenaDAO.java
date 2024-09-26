package repository;

import model.Cena;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement; // Importação correta

public class CenaDAO {
    private final Connection connection; // Conexão inicializada via construtor

    public CenaDAO(Connection connection) {
        this.connection = connection; // Inicializa a conexão
    }

    public Cena findCenaById(Integer id) throws SQLException {
        Cena cena = null; // Inicializa como null
        String sql = "SELECT * FROM cenas WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                cena = new Cena();
                cena.setId(rs.getInt("id"));
                cena.setDescricao(rs.getString("descricao")); // Mantém apenas a descrição
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar Cena com ID " + id + ": " + e.getMessage(), e);
        }
        
        return cena;
    }

    public int insertCena(Cena cena) throws SQLException {
        String insert = "INSERT INTO cenas(descricao) VALUES (?);"; // Mantém apenas a descrição
        int generatedId = -1; // Inicializa o ID gerado

        try (PreparedStatement ps = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, cena.getDescricao()); // Define a descrição
            ps.executeUpdate();

            // Obtém a chave gerada automaticamente
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1); // Define o id gerado
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir Cena: " + e.getMessage(), e);
        }
        
        return generatedId; // Retorna o ID gerado
    }
    
    public List<Cena> findAll() throws SQLException {
        String sql = "SELECT * FROM cenas;";
        List<Cena> cenas = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery()) {

            while (resultSet.next()) {
                Cena cena = new Cena();
                cena.setId(resultSet.getInt("id"));
                cena.setDescricao(resultSet.getString("descricao")); // Mantém apenas a descrição
                cenas.add(cena);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar todas as Cenas: " + e.getMessage(), e);
        }
        
        return cenas;
    }

    // Método para obter a cena inicial (se necessário)
    public Cena getCenaInicial() throws SQLException {
        return findCenaById(1); // Supondo que a cena inicial tenha o ID 1
    }
}
