package repository;

import model.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDAO {

    public static Item findItemByName(String nome) throws SQLException {
        String sql = "SELECT * FROM itens_da_cena WHERE nome = ?";

        try (Connection conn = Mysql.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Item item = new Item();
                    item.setIdItem(rs.getInt("id"));
                    item.setNome(rs.getString("nome"));
                    item.setDescricaoPositiva(rs.getString("descricao_positiva"));
                    item.setDescricaoNegativa(rs.getString("descricao_negativa"));
                    item.setComandoCorreto(rs.getString("comando_correto"));
                    item.setInteragivel(rs.getBoolean("interagivel"));
                    return item;
                } else {
                    return null;
                }
            }
        }
    }
}
