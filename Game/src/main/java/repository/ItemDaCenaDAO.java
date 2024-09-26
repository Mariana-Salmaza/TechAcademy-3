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
        if (!verificarCenaExistente(item.getIdCenaAtual()) || !verificarCenaExistente(item.getIdCenaDestino())) {
            throw new SQLException("Erro: ID de cena não existe.");
        }

        String sql = "INSERT INTO itens_da_cena (nome, descricao_positiva, descricao_negativa, " +
                     "comando_correto, id_cena_atual, id_cena_destino, interagivel) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getNome());
            statement.setString(2, item.getDescricaoPositiva());
            statement.setString(3, item.getDescricaoNegativa());
            statement.setString(4, item.getComandoCorreto());
            statement.setInt(5, item.getIdCenaAtual());
            statement.setInt(6, item.getIdCenaDestino());
            statement.setBoolean(7, item.getInteragivel());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        item.setId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Erro ao inserir item: nenhuma chave gerada.");
                    }
                }
                System.out.println("Item inserido com sucesso!");
            } else {
                System.out.println("Nenhum item foi inserido.");
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir item na cena: " + e.getMessage(), e);
        }
    }

    private boolean verificarCenaExistente(int idCena) throws SQLException {
        String sql = "SELECT COUNT(*) FROM cenas WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idCena);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    public List<ItemDaCena> listarItensDaCenaPorId(int idCena) throws SQLException {
        List<ItemDaCena> itens = new ArrayList<>();
        String sql = "SELECT * FROM itens_da_cena WHERE id_cena_atual = ? AND interagivel = true";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idCena);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ItemDaCena item = new ItemDaCena(
                            rs.getInt("id_item"),
                            rs.getString("nome"),
                            rs.getString("descricao_positiva"),
                            rs.getString("descricao_negativa"),
                            rs.getString("comando_correto"),
                            rs.getInt("id_cena_atual"),
                            rs.getInt("id_cena_destino"),
                            rs.getBoolean("interagivel"));
                    itens.add(item);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar itens da cena: " + e.getMessage(), e);
        }
        return itens;
    }


    public ItemDaCena buscarPorNome(String nome, Integer idSave) throws SQLException {
        String sql = "SELECT * FROM itens_da_cena WHERE nome = ? AND id_cena_atual = (SELECT id_cena_atual FROM saves WHERE id_save = ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nome);
            statement.setInt(2, idSave);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new ItemDaCena(
                            resultSet.getInt("id_item"),
                            resultSet.getString("nome"),
                            resultSet.getString("descricao_positiva"),
                            resultSet.getString("descricao_negativa"),
                            resultSet.getString("comando_correto"),
                            resultSet.getInt("id_cena_atual"),
                            resultSet.getInt("id_cena_destino"),
                            resultSet.getBoolean("interagivel"));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar item pelo nome: " + e.getMessage(), e);
        }
        return null;
    }
    public void atualizarItemDaCena(ItemDaCena item) throws SQLException {
        String sql = "UPDATE itens_da_cena SET nome = ?, descricao_positiva = ?, descricao_negativa = ?, " +
                     "comando_correto = ?, id_cena_atual = ?, id_cena_destino = ?, interagivel = ? WHERE id_item = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.getNome());
            statement.setString(2, item.getDescricaoPositiva());
            statement.setString(3, item.getDescricaoNegativa());
            statement.setString(4, item.getComandoCorreto());
            statement.setInt(5, item.getIdCenaAtual());
            statement.setInt(6, item.getIdCenaDestino());
            statement.setBoolean(7, item.getInteragivel());
            statement.setInt(8, item.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar item da cena: " + e.getMessage(), e);
        }
    }

    public void deletarItemDaCena(int id) throws SQLException {
        String sql = "DELETE FROM itens_da_cena WHERE id_item = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar item da cena: " + e.getMessage(), e);
        }
    }

    public boolean verificarUso(int idItemInventario, int idItemCena) throws SQLException {
        String sql = "SELECT COUNT(*) FROM use_with WHERE id_item_inventario = ? AND id_item_cena = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, idItemInventario);
            pstmt.setInt(2, idItemCena);
            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao verificar uso: " + e.getMessage(), e);
        }
    }

    public boolean verificarCompatibilidade(String itemInventarioNome, String itemCenaNome) throws SQLException {
        String sql = "SELECT COUNT(*) FROM use_with WHERE item_inventario = ? AND item_cena = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, itemInventarioNome);
            stmt.setString(2, itemCenaNome);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao verificar compatibilidade: " + e.getMessage(), e);
        }
    }
}
