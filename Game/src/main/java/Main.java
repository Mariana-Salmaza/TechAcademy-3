import controller.AntesDoJogoController;
import service.ComandoHelp;
import service.ComandoStart;
import spark.Spark;

public class Main {
    public static void main(String[] args) {

        Spark.port(4567); 

        ComandoHelp comandoHelp = new ComandoHelp();
        ComandoStart comandoStart = new ComandoStart();

        AntesDoJogoController controller = new AntesDoJogoController(comandoHelp, comandoStart);

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
