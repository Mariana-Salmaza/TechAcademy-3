package repository;

import model.Inventario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class InventarioDAO {

    public static void insertInventario(Inventario inventario) throws SQLException {
        String sql = "INSERT INTO itens_inventario (id_save, nome_jogador, nome_item, descricao) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = Mysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, inventario.getIdSave());
            stmt.setString(2, inventario.getNomeJogador());
            stmt.setString(3, inventario.getNomeItem());
            stmt.setString(4, inventario.getDescricao());
            
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
                    inventario.setIdSave(rs.getInt("id_save"));
                    inventario.setNomeJogador(rs.getString("nome_jogador"));
                    inventario.setNomeItem(rs.getString("nome_item"));
                    inventario.setDescricao(rs.getString("descricao"));
                }
            }
        }
        return inventario;
    }
    
    public static List<Inventario> findInventariosByNomeJogador(String nomeJogador) throws SQLException {
        String sql = "SELECT * FROM itens_inventario WHERE nome_jogador = ?";
        List<Inventario> inventarios = new ArrayList<>();
        
        try (Connection conn = Mysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nomeJogador);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Inventario inventario = new Inventario();
                    inventario.setIdInventario(rs.getInt("id"));
                    inventario.setIdSave(rs.getInt("id_save"));
                    inventario.setNomeJogador(rs.getString("nome_jogador"));
                    inventario.setNomeItem(rs.getString("nome_item"));
                    inventario.setDescricao(rs.getString("descricao"));
                    inventarios.add(inventario);
                }
            }
        }
        return inventarios;
    }

    public static JsonElement getItensByIdSave(Integer idSave) throws SQLException {
        String sql = "SELECT * FROM itens_inventario WHERE id_save = ?";
        JsonArray jsonArray = new JsonArray();
        
        try (Connection conn = Mysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idSave);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("id", rs.getInt("id"));
                    jsonObject.addProperty("id_save", rs.getInt("id_save"));
                    jsonObject.addProperty("nome_jogador", rs.getString("nome_jogador"));
                    jsonObject.addProperty("nome_item", rs.getString("nome_item"));
                    jsonObject.addProperty("descricao", rs.getString("descricao"));
                    jsonArray.add(jsonObject);
                }
            }
        }
        return jsonArray;
    }

    public static Integer getIdSave() throws SQLException {
        String sql = "SELECT COALESCE(MAX(id_save), 0) + 1 AS new_id_save FROM itens_inventario";
        try (Connection conn = Mysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            if (rs.next()) {
                return rs.getInt("new_id_save");
            } else {
                throw new SQLException("Não foi possível obter o próximo id_save.");
            }
        }
    }
}
