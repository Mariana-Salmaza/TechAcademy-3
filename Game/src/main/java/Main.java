package main.java;
import controller.AntesDoJogoController;
import service.ComandoHelp;
import service.ComandoStart;
import service.ComandoUse;
import service.ComandoGet;
import service.ComandoInventory;
import service.ComandoCheck;
import service.ComandoLoad;
import spark.Spark;

public class Main {
    public static void main(String[] args) {
        Spark.port(4567);

        ComandoHelp comandoHelp = new ComandoHelp();
        ComandoStart comandoStart = new ComandoStart();
        ComandoUse comandoUse = new ComandoUse();
        ComandoGet comandoGet = new ComandoGet();
        ComandoInventory comandoInventory = new ComandoInventory();
        ComandoCheck comandoCheck = new ComandoCheck();
        ComandoLoad comandoLoad = new ComandoLoad();

        AntesDoJogoController controller = new AntesDoJogoController(
            comandoHelp, comandoStart, comandoUse, comandoCheck, comandoGet, comandoInventory, comandoLoad
        );

        Spark.get("/:comando", controller);

        Spark.options("/*", (request, response) -> {
            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        Spark.after((request, response) -> response.header("Access-Control-Allow-Origin", "*"));
    }
}
