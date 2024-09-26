package repository;

import model.UseWith;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UseWithDAO {
    private final Connection connection;

    public UseWithDAO(Connection connection) {
        this.connection = connection;
    }

    public void inserirUseWith(UseWith useWith) throws SQLException {
        String sql = "INSERT INTO use_with (id_item_inventario, id_item_cena, descricao_acao) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, useWith.getIdItemInventario());
            statement.setInt(2, useWith.getIdItemCena());
            statement.setString(3, useWith.getDescricaoAcao());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    useWith.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir UseWith: " + e.getMessage(), e);
        }
    }


    public List<UseWith> findActionsByItemId(int idItemInventario) throws SQLException {
        List<UseWith> useWithList = new ArrayList<>();
        String sql = "SELECT * FROM use_with WHERE id_item_inventario = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idItemInventario);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    UseWith useWith = new UseWith(
                            resultSet.getInt("id"),
                            resultSet.getInt("id_item_inventario"),
                            resultSet.getInt("id_item_cena"),
                            resultSet.getString("descricao_acao")
                    );
                    useWithList.add(useWith);
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar ações por ID do item do inventário: " + e.getMessage(), e);
        }
        return useWithList;
    }


    public void deletarUseWith(int idItemInventario, int idItemCena) throws SQLException {
        String sql = "DELETE FROM use_with WHERE id_item_inventario = ? AND id_item_cena = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idItemInventario);
            statement.setInt(2, idItemCena);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar UseWith: " + e.getMessage(), e);
        }
    }
    public UseWith buscarUseWithPorId(int id) throws SQLException {
        String sql = "SELECT * FROM use_with WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return new UseWith(
                            resultSet.getInt("id"),
                            resultSet.getInt("id_item_inventario"),
                            resultSet.getInt("id_item_cena"),
                            resultSet.getString("descricao_acao")
                    );
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar UseWith por ID: " + e.getMessage(), e);
        }
        return null;
    }
}
