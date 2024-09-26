package repository;

import model.UseWith;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UseWithDAO {
    private final Connection connection;

    public UseWithDAO(Connection connection) {
        this.connection = connection;
    }

    public void adicionarRelacao(UseWith useWith) throws SQLException {
        String sql = "INSERT INTO use_with (id_item_inventario, id_item_cena) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, useWith.getIdItemInventario());
            stmt.setInt(2, useWith.getIdItemCena());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao adicionar relação entre itens: " + e.getMessage(), e);
        }
    }

   
    public List<UseWith> listarRelacoes() throws SQLException {
        List<UseWith> relacoes = new ArrayList<>();
        String sql = "SELECT * FROM use_with";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                UseWith useWith = new UseWith();
                useWith.setIdItemInventario(rs.getInt("id_item_inventario"));
                useWith.setIdItemCena(rs.getInt("id_item_cena"));
                relacoes.add(useWith);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar relações de uso: " + e.getMessage(), e);
        }

        return relacoes;
    }

    public boolean podeUsar(int idItemInventario, int idItemCena) throws SQLException {
        String sql = "SELECT COUNT(*) FROM use_with WHERE id_item_inventario = ? AND id_item_cena = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idItemInventario);
            stmt.setInt(2, idItemCena);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; 
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao verificar relação de uso: " + e.getMessage(), e);
        }
        return false;
    }


    public void removerRelacao(int idItemInventario, int idItemCena) throws SQLException {
        String sql = "DELETE FROM use_with WHERE id_item_inventario = ? AND id_item_cena = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, idItemInventario);
            stmt.setInt(2, idItemCena);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao remover relação de uso: " + e.getMessage(), e);
        }
    }
}
