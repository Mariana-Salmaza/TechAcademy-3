package repository;

import com.mysql.cj.MysqlConnection;
import model.Cena;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CenaDAO {
    public static Cena findCenaById(Integer id) throws SQLException {
        
        String sql = "SELECT * FROM cenas WHERE id = ?";
        Cena cena = new Cena();
        
        try (Connection conn = Mysql.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {

                if(rs.next()) {
                    cena.setIdCena(rs.getInt("id"));
                    cena.setDescricao(rs.getString("descricao"));
                }
            }
        }
        return cena;    
    }
}
