package controller;

import com.google.gson.Gson;
import model.ItemDaCena;
import repository.ItemDaCenaDAO;
import spark.Request;
import spark.Response;
import spark.Route;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DuranteOJogoController {
    private ItemDaCenaDAO itemDaCenaDAO = null;
    private Gson gson = new Gson();

    public DuranteOJogoController(Connection connection) {
        this.itemDaCenaDAO = new ItemDaCenaDAO(connection);
    }

    public Route usarItem = (Request req, Response res) -> {
        int idItemInventario = Integer.parseInt(req.queryParams("id_item_inventario"));
        int idItemCena = Integer.parseInt(req.queryParams("id_item_cena"));

        try {
            boolean podeUsar = itemDaCenaDAO.verificarUso(idItemInventario, idItemCena);
            if (podeUsar) {
                res.status(200);
                return "Item pode ser usado.";
            } else {
                res.status(403);
                return "Item não pode ser usado aqui.";
            }
        } catch (SQLException e) {
            res.status(500);
            return "Erro ao verificar uso do item: " + e.getMessage();
        } catch (NumberFormatException e) {
            res.status(400);
            return "Erro: parâmetros inválidos.";
        }
    };

    public Route obterItensDaCena = (Request req, Response res) -> {
        int idCena = Integer.parseInt(req.queryParams("id_cena"));
        List<ItemDaCena> itens;

        try {
            itens = itemDaCenaDAO.listarItensDaCenaPorId(idCena);
            res.type("application/json");
            return gson.toJson(itens);
        } catch (SQLException e) {
            res.status(500);
            return "Erro ao obter itens da cena: " + e.getMessage();
        } catch (NumberFormatException e) {
            res.status(400);
            return "Erro: parâmetros inválidos.";
        }
    };

    public Route tratarComando = (Request req, Response res) -> {
        String comando = req.params(":comando");
        String save = req.params(":save");
        res.status(200);
        return "Comando " + comando + " tratado com sucesso.";
    };
}
