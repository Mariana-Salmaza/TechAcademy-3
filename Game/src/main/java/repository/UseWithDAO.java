package repository;

import model.UseWith;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UseWithDAO {

    
    public static List<UseWith> findActionsByItemId(int idItemInventario) throws SQLException {
        List<UseWith> useWithList = new ArrayList<>();
        Connection connection = Mysql.getConnection(); 
        String sql = "SELECT * FROM use_with WHERE id_item_inventario = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, idItemInventario);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            UseWith useWith = new UseWith();
            useWith.setId(rs.getInt("id"));
            useWith.setIdItemInventario(rs.getInt("id_item_inventario"));
            useWith.setIdItemCena(rs.getInt("id_item_cena"));
            useWith.setDescricaoAcao(rs.getString("descricao_acao"));
            useWithList.add(useWith);
        }

        rs.close();
        stmt.close();
        connection.close();

        return useWithList;
    }

}
