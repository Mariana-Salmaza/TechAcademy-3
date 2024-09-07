package repository;

import model.Cena;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CenaDAO {

    public static Cena findCenaById(int id) throws SQLException {
        String sql = "SELECT * FROM cenas WHERE id = ?";
        Cena cena = null;

        try (Connection conn = Mysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id); 

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cena = new Cena();
                    cena.setId(rs.getInt("id")); 
                    cena.setDescricao(rs.getString("descricao"));
                }
            }
        }
        return cena;
    }

    public static void insertCena(Cena cena) throws SQLException {
        String insert = "INSERT INTO cenas(descricao) VALUES (?);";

        try (Connection conn = Mysql.getConnection();
             PreparedStatement ps = conn.prepareStatement(insert)) {

            ps.setString(1, cena.getDescricao());
            ps.executeUpdate();
        }
    }
}
