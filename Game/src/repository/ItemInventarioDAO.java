package repository;

import model.ItemInventario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemInventarioDAO {
    private final Connection connection;

    public ItemInventarioDAO(Connection connection) {
        this.connection = connection;
    }

    public List<ItemInventario> buscarPorIdSave(Integer idSave) throws SQLException {
        List<ItemInventario> inventario = new ArrayList<>();
        String sql = "SELECT * FROM itens_inventario WHERE id_save = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idSave);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ItemInventario item = new ItemInventario(
                            rs.getInt("id"),
                            rs.getString("nome_item"),
                            rs.getString("descricao_positiva"),
                            rs.getString("descricao_negativa"),
                            rs.getInt("id_save")
                    );
                    inventario.add(item);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar itens do inventário: " + e.getMessage(), e);
        }

        return inventario;
    }

    public ItemInventario buscarPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM itens_inventario WHERE nome_item = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nome);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new ItemInventario(
                            rs.getInt("id"),
                            rs.getString("nome_item"),
                            rs.getString("descricao_positiva"),
                            rs.getString("descricao_negativa"),
                            rs.getInt("id_save")
                    );
                }
            }
        }
        return null;
    }

    public void adicionarItem(ItemInventario item) throws SQLException {
        String sql = "INSERT INTO itens_inventario (nome_item, descricao_positiva, descricao_negativa, id_save) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, item.getNome());
            stmt.setString(2, item.getDescricaoPositiva());
            stmt.setString(3, item.getDescricaoNegativa());
            stmt.setInt(4, item.getIdSave());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao adicionar item ao inventário: " + e.getMessage(), e);
        }
    }

    public List<ItemInventario> listarTodos() throws SQLException {
        List<ItemInventario> inventario = new ArrayList<>();
        String sql = "SELECT * FROM itens_inventario";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                ItemInventario item = new ItemInventario(
                        rs.getInt("id"),
                        rs.getString("nome_item"),
                        rs.getString("descricao_positiva"),
                        rs.getString("descricao_negativa"),
                        rs.getInt("id_save")
                );
                inventario.add(item);
            }
        }
        return inventario;
    }

    public List<ItemInventario> listarItensPorSave(int idSave) {
        throw new UnsupportedOperationException("Unimplemented method 'listarItensPorSave'");
    }
}
