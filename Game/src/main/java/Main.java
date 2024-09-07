import controller.AntesDoJogoController;
import service.ComandoHelp;
import service.ComandoStart;
import spark.Spark;

public class Main {
    public static void main(String[] args) {

        ComandoHelp comandoHelp = new ComandoHelp();
        ComandoStart comandoStart = new ComandoStart();

        AntesDoJogoController controller = new AntesDoJogoController(comandoHelp, comandoStart);

        Spark.get("/:comando", controller);

        Spark.port(4567);
    }
}
