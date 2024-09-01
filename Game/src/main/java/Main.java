import model.Cena;
import model.Item;
import repository.CenaDAO;
import repository.ItemDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            // Busca e exibe a cena com ID 1
            Cena cena = CenaDAO.findCenaById(1);
            System.out.println(cena.toString());

            // Solicita ao usuário o nome do item
            Scanner scanner = new Scanner(System.in);
            
            while(true) {
                System.out.println("Digite o nome de um item para obter sua descrição:");
                String nomeItem = scanner.nextLine();
    
                // Busca o item pelo nome
                Item item = ItemDAO.findItemByName(nomeItem);
    
                // Exibe a descrição do item ou uma mensagem de erro se o item não for encontrado
                if (item != null) {
                    System.out.println("Descrição do item: " + item.getDescricaoPositiva());
                } else {
                    System.out.println("Item não encontrado.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
