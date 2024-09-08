package repository;

import model.Inventario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {

    public static void insertInventario(Inventario inventario) throws SQLException {
        String sql = "INSERT INTO itens_inventario (nome_item, descricao) VALUES (?, ?)";
        
        try (Connection conn = Mysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, inventario.getNomeItem());
            stmt.setString(2, inventario.getDescricao());
            
            stmt.executeUpdate();
        }
    }

    public static Inventario findInventarioById(Integer id) throws SQLException {
        String sql = "SELECT * FROM itens_inventario WHERE id = ?";
        Inventario inventario = null;
        
        try (Connection conn = Mysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    inventario = new Inventario();
                    inventario.setIdInventario(rs.getInt("id"));
                    inventario.setNomeItem(rs.getString("nome_item"));
                    inventario.setDescricao(rs.getString("descricao"));
                }
            }
        }
        return inventario;
    }

    public static List<Inventario> findInventariosByNomeItem(String nomeItem) throws SQLException {
        String sql = "SELECT * FROM itens_inventario WHERE nome_item = ?";
        List<Inventario> inventarios = new ArrayList<>();
        
        try (Connection conn = Mysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeItem);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Inventario inventario = new Inventario();
                    inventario.setIdInventario(rs.getInt("id"));
                    inventario.setNomeItem(rs.getString("nome_item"));
                    inventario.setDescricao(rs.getString("descricao"));
                    inventarios.add(inventario);
                }
            }
        }
        return inventarios;
    }

    public static Integer getNextIdSave() throws SQLException {
        String sql = "SELECT COALESCE(MAX(id), 0) + 1 AS new_id FROM itens_inventario";
        try (Connection conn = Mysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("new_id");
            } else {
                throw new SQLException("Não foi possível obter o próximo id.");
            }
        }
    }
}
