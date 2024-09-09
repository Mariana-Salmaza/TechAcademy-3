package controller;

import com.google.gson.Gson;
import service.ComandoHelp;
import service.ComandoStart;
import service.ComandoUse;
import service.ComandoCheck;
import service.ComandoGet;
import service.ComandoInventory;
import service.ComandoLoad;
import spark.Request;
import spark.Response;
import spark.Route;

public class AntesDoJogoController implements Route {
    private final Gson gson = new Gson(); 
    private final ComandoHelp comandoHelp;
    private final ComandoStart comandoStart;
    private final ComandoUse comandoUse;
    private final ComandoCheck comandoCheck;
    private final ComandoGet comandoGet;
    private final ComandoInventory comandoInventory;
    private final ComandoLoad comandoLoad;

    public AntesDoJogoController(ComandoHelp comandoHelp, ComandoStart comandoStart, ComandoUse comandoUse, 
                                ComandoCheck comandoCheck, ComandoGet comandoGet, ComandoInventory comandoInventory,
                                ComandoLoad comandoLoad) {
        this.comandoHelp = comandoHelp;
        this.comandoStart = comandoStart;
        this.comandoUse = comandoUse;
        this.comandoCheck = comandoCheck;
        this.comandoGet = comandoGet;
        this.comandoInventory = comandoInventory;
        this.comandoLoad = comandoLoad;
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
                if (comandos[0].equalsIgnoreCase("use")) {
                    return gson.toJson(comandoUse.executar(comandos[1], comandoBruto));
                }

                if (comandos[0].equalsIgnoreCase("check")) {
                    return gson.toJson(comandoCheck.executar(comandos[1]));
                }
                
                if (comandos[0].equalsIgnoreCase("get")) {
                    return gson.toJson(comandoGet.executar(comandos[1]));
                }
                
                if (comandos[0].equalsIgnoreCase("load") && comandos.length == 2) {
                    int idSave = Integer.parseInt(comandos[1]);
                    return gson.toJson(comandoLoad.executar(idSave));
                }
                
                return "Comando não reconhecido ou argumentos incorretos.";
            } else if (comandos[0].equalsIgnoreCase("inventory")) {
                return gson.toJson(comandoInventory.executar());
            } else {
                return "O comando digitado foi: " + comandos[0];
            }
        } catch (Exception e) {
            response.status(500); 
            return "Erro ao processar o comando: " + e.getMessage();
        }
    }
}
