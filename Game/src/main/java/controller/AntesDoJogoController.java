package controller;

import com.google.gson.Gson;
import service.ComandoHelp;
import service.ComandoStart;
import spark.Request;
import spark.Response;
import spark.Route;

public class AntesDoJogoController implements Route {
    private final Gson gson = new Gson(); 
    private final ComandoHelp comandoHelp;
    private final ComandoStart comandoStart;

    public AntesDoJogoController(ComandoHelp comandoHelp, ComandoStart comandoStart) {
        this.comandoHelp = comandoHelp;
        this.comandoStart = comandoStart;
    }

    @Override
    public Object handle(Request request, Response response) {
        String comandoBruto = request.params(":comando");
        String[] comandos = comandoBruto.split(" ");

        try {
            if (comandos.length == 0) {
                return "Nenhum comando fornecido.";
            }

            if (comandos[0].equalsIgnoreCase("help")) {
                return gson.toJson(comandoHelp.executar());
            }

            if (comandos[0].equalsIgnoreCase("start")) {
                return gson.toJson(comandoStart.executar());
            }

            if (comandos.length > 1) {
                return "O comando digitado foi: " + comandos[0] + " e o argumento " + comandos[1];
            } else {
                return "O comando digitado foi: " + comandos[0];
            }
        } catch (Exception e) {
            response.status(500); 
            return "Erro ao processar o comando: " + e.getMessage();
        }
    }
}
