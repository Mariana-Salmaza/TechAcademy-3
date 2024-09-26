import service.ComandoService;
import repository.CenaDAO;
import repository.ItemDaCenaDAO;
import repository.ItemInventarioDAO;
import repository.SaveDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Configuração da conexão com o banco de dados
        String url = "jdbc:mysql://localhost:3306/game"; // Altere para o URL do seu banco de dados
        String user = "root"; // Altere para seu usuário
        String password = ""; // Altere para sua senha

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            // Inicializando os DAOs com a conexão
            ItemInventarioDAO itemInventarioDAO = new ItemInventarioDAO(connection);
            ItemDaCenaDAO itemDaCenaDAO = new ItemDaCenaDAO(connection);
            CenaDAO cenaDAO = new CenaDAO(connection);
            SaveDAO saveDAO = new SaveDAO(connection);

            // Inicializando o ComandoService com os DAOs
            ComandoService comandoService = new ComandoService(itemInventarioDAO, itemDaCenaDAO, cenaDAO, saveDAO);

            // Iniciar o jogo
            comandoService.start();

            Scanner scanner = new Scanner(System.in);
            String comando;

            System.out.println("Bem-vindo ao jogo! Digite /help para ver a lista de comandos.");

            while (true) {
                System.out.print("Digite um comando: ");
                comando = scanner.nextLine().trim();
            
                switch (comando.toLowerCase()) {
                    case "/help":
                        System.out.println(comandoService.help());
                        break;
                    case "/restart":
                        comandoService.restart();
                        break;
                    case "/inventory":
                        comandoService.mostrarInventario();
                        break;
                    default:
                        if (comando.startsWith("/get ")) {
                            String itemNome = comando.substring(5);
                            comandoService.pegarItem(itemNome);
                        } else if (comando.startsWith("/use ")) {
                            String itemNome = comando.substring(5);
                            comandoService.usarItem(itemNome);
                        } else if (comando.startsWith("/usewith ")) {
                            String[] partes = comando.split(" ");
                            if (partes.length >= 3) {
                                String itemInventarioNome = partes[1];
                                String itemCenaNome = partes[2];
                                comandoService.usarCom(itemInventarioNome, itemCenaNome);
                            } else {
                                System.out.println("Uso incorreto do comando /usewith. Exemplo: /usewith itemInventario itemCena");
                            }
                        } else {
                            System.out.println("Comando não reconhecido. Digite /help para ver a lista de comandos.");
                        }
                        break;
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }
}
