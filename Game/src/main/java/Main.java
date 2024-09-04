package main.java;

import main.java.model.Cena;
import main.java.model.Item;
import main.java.model.Save;
import main.java.repository.CenaDAO;
import main.java.repository.ItemDAO;
import main.java.repository.SaveDAO;


import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {

            Cena cena = CenaDAO.findCenaById(1);
            System.out.println(cena.toString());


            Scanner scanner = new Scanner(System.in);
            
//            while(true) {
//                System.out.println("Digite o nome de um item para obter sua descrição:");
//                String nomeItem = scanner.nextLine();
//
//
//                Item item = ItemDAO.findItemByName(nomeItem);
//
//                if (item != null) {
//                    System.out.println("Descrição do item: " + item.getDescricaoPositiva());
//                } else {
//                    System.out.println("Item não encontrado.");
//                }
//            }

            Save save = SaveDAO.novoJogo();
            System.out.println(save.getCenaAtual());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
