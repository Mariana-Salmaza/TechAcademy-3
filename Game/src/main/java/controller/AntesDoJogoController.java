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

public class AntesDoJogoController {
    private ItemDaCenaDAO itemDaCenaDAO = null;
    private Gson gson = new Gson();

    public AntesDoJogoController(Connection connection) {
        this.itemDaCenaDAO = new ItemDaCenaDAO(connection);
    }

    public Route adicionarItem = (Request req, Response res) -> {
        try {
            int idItem = Integer.parseInt(req.queryParams("idItem"));
            String nome = req.queryParams("nome");
            String descricaoPositiva = req.queryParams("descricao_positiva");
            String descricaoNegativa = req.queryParams("descricao_negativa");
            String comandoCorreto = req.queryParams("comando_correto");
            int idCenaAtual = Integer.parseInt(req.queryParams("id_cena_atual"));
            int idCenaDestino = Integer.parseInt(req.queryParams("id_cena_destino"));
            boolean interagivel = Boolean.parseBoolean(req.queryParams("interagivel"));

            ItemDaCena item = new ItemDaCena(idItem, nome, descricaoPositiva, descricaoNegativa, comandoCorreto, idCenaAtual, idCenaDestino, interagivel);

            itemDaCenaDAO.inserirItemDaCena(item);
            res.status(201); // Created
            return "Item inserido com sucesso!";
        } catch (SQLException e) {
            res.status(500); // Internal Server Error
            return "Erro ao inserir item: " + e.getMessage();
        } catch (NumberFormatException e) {
            res.status(400); // Bad Request
            return "Erro: parâmetros inválidos.";
        }
    };

    public Route listarItensDaCena = (Request req, Response res) -> {
        int idCena = Integer.parseInt(req.queryParams("id_cena"));
        List<ItemDaCena> itens;

        try {
            itens = itemDaCenaDAO.listarItensDaCenaPorId(idCena);
            res.type("application/json");
            return gson.toJson(itens);
        } catch (SQLException e) {
            res.status(500);
            return "Erro ao listar itens: " + e.getMessage();
        } catch (NumberFormatException e) {
            res.status(400); // Bad Request
            return "Erro: parâmetros inválidos.";
        }
    };
}
