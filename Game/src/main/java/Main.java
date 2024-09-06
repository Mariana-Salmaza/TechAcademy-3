import com.google.gson.Gson;
import model.Cena;
import model.Save;
import repository.CenaDAO;
import repository.InventarioDAO;
import spark.Spark;

public class Main {
    private static final Gson GSON = new Gson();

    public static void main(String[] args) {
        try {
            Spark.port(4567);

            
            Spark.get("/", (req, res) -> {
                res.type("application/json");
                Integer idSave = InventarioDAO.getIdSave(); 
                Save save = new Save(idSave);
                return GSON.toJson(save);
            });

            Spark.get("/cena/:id", (req, res) -> {
                res.type("application/json");
                Integer cenaId = Integer.parseInt(req.params(":id"));
                Cena cena = CenaDAO.findCenaById(cenaId);
                return GSON.toJson(cena);
            });

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
