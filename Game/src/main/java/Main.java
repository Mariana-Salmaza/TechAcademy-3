import model.Save;
import repository.ItemDaCenaDAO;
import repository.CenaDAO;
import service.ComandoService;
import controller.AntesDoJogoController;
import controller.DuranteOJogoController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/game";
    private static final String USER = "root";
    private static final String PASS = "";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASS)) {
            ItemDaCenaDAO itemDaCenaDAO = new ItemDaCenaDAO(connection);
            CenaDAO cenaDAO = new CenaDAO(connection);
            ComandoService comandoService = new ComandoService(itemDaCenaDAO, cenaDAO);
            AntesDoJogoController antesDoJogoController = new AntesDoJogoController(comandoService);
            Save save = new Save();
            DuranteOJogoController duranteOJogoController = new DuranteOJogoController(comandoService, save);
    
            Scanner scanner = new Scanner(System.in);
            String comando;
    
            // Lógica antes do jogo
            System.out.println("\nDigite 'start' para iniciar o jogo ou 'exit' para sair.\n");
            while (true) {
                comando = scanner.nextLine().trim();
                if (comando.equalsIgnoreCase("exit")) {
                    System.out.println("\nSaindo do jogo. Até logo!\n");
                    return;
                }
    
                String resposta = antesDoJogoController.iniciarJogo(comando);
                System.out.println("\n" + resposta + "\n");
                if (resposta.contains("Jogo iniciado")) {
                    break; 
                }
            }
    
            System.out.println("\nVocê pode começar a jogar! Digite um comando:\n");
            while (true) {
                comando = scanner.nextLine().trim();
                if (comando.equalsIgnoreCase("exit")) {
                    System.out.println("\nSaindo do jogo. Até logo!\n");
                    return;
                }
    
                String resposta = duranteOJogoController.processarComando(comando);
                System.out.println("\n" + resposta + "\n");
            }
        } catch (SQLException e) {
            System.out.println("\nErro ao conectar ao banco de dados: " + e.getMessage() + "\n");
        }
    }
    
}
