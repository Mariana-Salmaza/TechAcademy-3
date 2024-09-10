package repository;

import model.Inventario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {
    
    public static List<Inventario> findInventariosBySaveId(int idSave) throws SQLException {
        List<Inventario> inventarios = new ArrayList<>();
        String query = "SELECT * FROM inventario WHERE id_save = ?";

        try (Connection connection = Mysql.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idSave); 
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Inventario inventario = new Inventario();
                    inventario.setIdInventario(resultSet.getInt("id"));
                    inventario.setNomeItem(resultSet.getString("nome_item"));
                    inventario.setDescricao(resultSet.getString("descricao"));
                    inventario.setId_save(resultSet.getInt("id_save"));
                    inventarios.add(inventario);
                }
            }
        }
        return inventarios;
    }
  
    public static List<Inventario> findAllInventarios() {
        throw new UnsupportedOperationException("Unimplemented method 'findAllInventarios'");
    }

    public static void clearInventarioBySaveId(int idSave) {
        throw new UnsupportedOperationException("Unimplemented method 'clearInventarioBySaveId'");
    }

    public static List<Inventario> findInventariosByNomeItem(String itemInventarioNome) {
       
        throw new UnsupportedOperationException("Unimplemented method 'findInventariosByNomeItem'");
    }
}
