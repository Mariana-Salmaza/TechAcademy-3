package repository;

import model.ItemDaCena;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemDaCenaDAO {
    private final Connection connection;

    public ItemDaCenaDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserirItemDaCena(ItemDaCena item) throws SQLException {
        String sql = "INSERT INTO itens_da_cena (nome, descricao_positiva, descricao_negativa, comando_correto, id_cena_atual, id_cena_destino, interagivel) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getNome());
            statement.setString(2, item.getDescricaoPositiva());
            statement.setString(3, item.getDescricaoNegativa());
            statement.setString(4, item.getComandoCorreto());
            statement.setInt(5, item.getIdCenaAtual());
            statement.setInt(6, item.getIdCenaDestino());
            statement.setBoolean(7, item.isInteragivel());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setIdItem(generatedKeys.getInt(1));
                }
            }
        }
    }

    public List<ItemDaCena> listarItensDaCenaPorId(int idCena) throws SQLException {
        List<ItemDaCena> itens = new ArrayList<>();
        String sql = "SELECT * FROM itens_da_cena WHERE id_cena_atual = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idCena);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    ItemDaCena item = new ItemDaCena(
                            resultSet.getInt("id"),
                            resultSet.getString("nome"),
                            resultSet.getString("descricao_positiva"),
                            resultSet.getString("descricao_negativa"),
                            resultSet.getString("comando_correto"),
                            resultSet.getInt("id_cena_atual"),
                            resultSet.getInt("id_cena_destino"),
                            resultSet.getBoolean("interagivel"));
                    itens.add(item);
                }
            }
        }
        return itens;
    }

    public ItemDaCena buscarPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM itens_da_cena WHERE nome = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new ItemDaCena(
                            resultSet.getInt("id"),
                            resultSet.getString("nome"),
                            resultSet.getString("descricao_positiva"),
                            resultSet.getString("descricao_negativa"),
                            resultSet.getString("comando_correto"),
                            resultSet.getInt("id_cena_atual"),
                            resultSet.getInt("id_cena_destino"),
                            resultSet.getBoolean("interagivel"));
                }
            }
        }
        return null;
    }

    public void atualizarItemDaCena(ItemDaCena item) throws SQLException {
        String sql = "UPDATE itens_da_cena SET nome = ?, descricao_positiva = ?, descricao_negativa = ?, comando_correto = ?, id_cena_atual = ?, id_cena_destino = ?, interagivel = ? WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.getNome());
            statement.setString(2, item.getDescricaoPositiva());
            statement.setString(3, item.getDescricaoNegativa());
            statement.setString(4, item.getComandoCorreto());
            statement.setInt(5, item.getIdCenaAtual());
            statement.setInt(6, item.getIdCenaDestino());
            statement.setBoolean(7, item.isInteragivel());
            statement.setInt(8, item.getIdItem());
            statement.executeUpdate();
        }
    }

    public void deletarItemDaCena(int id) throws SQLException {
        String sql = "DELETE FROM itens_da_cena WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public boolean verificarUso(int idItemInventario, int idItemCena) throws SQLException {
        String sql = "SELECT COUNT(*) FROM use_with WHERE id_item_inventario = ? AND id_item_cena = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idItemInventario);
            pstmt.setInt(2, idItemCena);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }
}
