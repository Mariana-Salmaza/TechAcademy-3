package service;

import model.Save;
import model.Cena;
import repository.CenaDAO;
import repository.ItemDaCenaDAO;

import java.util.ArrayList;
import java.util.List;

public class ComandoService {
    private final ItemDaCenaDAO itemDaCenaDAO;
    private final CenaDAO cenaDAO;
    private List<String> inventario;

    public ComandoService(ItemDaCenaDAO itemDaCenaDAO, CenaDAO cenaDAO) {
        this.itemDaCenaDAO = itemDaCenaDAO;
        this.cenaDAO = cenaDAO;
        this.inventario = new ArrayList<>();
    }

    public String processarComando(String comando, Save save) {
        System.out.println("Estado atual do Save: idCenaAtual = " + save.getIdCenaAtual());

        if (save.getIdCenaAtual() == 0) {
            return start(save);
        }

        switch (comando.toLowerCase()) {
            case "help":
                return obterDescricaoCena(save) + "\n" + help();
            case "restart":
                return reiniciarJogo(save) + "\n" + obterDescricaoCena(save);
            case "inventory":
                return mostrarInventario() + "\n" + obterDescricaoCena(save);
            default:
                if (comando.startsWith("get ")) {
                    String resultadoGet = pegarItem(save, comando.substring(4).trim());
                    return resultadoGet + "\n" + obterDescricaoCena(save);
                } else if (comando.startsWith("use ")) {
                    String resultadoUse = usarItem(save, comando.substring(4).trim());
                    return resultadoUse + "\n" + obterDescricaoCena(save);
                } else {
                    return "Comando não reconhecido.\n" + obterDescricaoCena(save);
                }
        }
    }

    private String start(Save save) {
        save.setIdCenaAtual(1);
        System.out.println("Iniciando o jogo... Cena atual: " + save.getIdCenaAtual());
        return obterDescricaoCena(save);
    }

    private String reiniciarJogo(Save save) {
        save.setIdCenaAtual(1);
        System.out.println("Reiniciando o jogo... Cena atual: " + save.getIdCenaAtual());
        return obterDescricaoCena(save);
    }

    private String obterDescricaoCena(Save save) {
        try {
            Cena cena = cenaDAO.getCenaPorId(save.getIdCenaAtual());
            if (cena != null) {
                return "Você está na cena " + cena.getId() + ": " + cena.getDescricao() +
                       "\nComandos disponíveis: help, restart, inventory, use <item>, get <item>." +
                       "\nDigite um comando para continuar.";
            } else {
                return "Cena não encontrada.";
            }
        } catch (Exception e) {
            return "Erro ao buscar descrição da cena: " + e.getMessage();
        }
    }

    public String usarItem(Save save, String item) {
        try {
            System.out.println("Usando item: " + item + " na cena: " + save.getIdCenaAtual());

            switch (save.getIdCenaAtual()) {
                case 1:
                    if (item.equalsIgnoreCase("LANTERNA")) {
                        save.setIdCenaAtual(2);
                        System.out.println("Avançando para a cena 2.");
                        return "Você usou a LANTERNA corretamente! Avançando para a próxima cena...";
                    } else {
                        return "Esse item não serve para esse caso, tente novamente.";
                    }

                case 2:
                    if (item.equalsIgnoreCase("CHAVE DE FERRO")) {
                        save.setIdCenaAtual(3);
                        System.out.println("Avançando para a cena 3.");
                        return "Você usou a CHAVE DE FERRO e abriu a porta! Avançando para a próxima cena...";
                    }
                    return "Esse item não serve para esse caso, tente novamente.";

                case 3:
                    if (item.equalsIgnoreCase("ESPADA DE PRATA")) {
                        // Implementar lógica específica para usar a espada, se necessário
                        return "Você usou a ESPADA DE PRATA.";
                    }
                    return "Esse item não serve para esse caso, tente novamente.";

                case 4:
                    if (item.equalsIgnoreCase("TALISMAN")) {
                        save.setIdCenaAtual(5);
                        System.out.println("Avançando para a cena 5.");
                        return "Você usou o TALISMAN! Avançando para a próxima cena...";
                    }
                    return "Esse item não serve para esse caso, tente novamente.";

                case 5:
                    if (item.equalsIgnoreCase("CARTAS ANTIGAS")) {
                        save.setIdCenaAtual(6);
                        System.out.println("Avançando para a cena 6.");
                        return "Você usou as CARTAS ANTIGAS! Avançando para a sala do tesouro...";
                    }
                    return "Esse item não serve para esse caso, tente novamente.";

                case 6:
                    return "Parabéns, você chegou à sala do tesouro! Você ganhou!";

                default:
                    return "Cena não reconhecida.";
            }
        } catch (Exception e) {
            return "Erro ao usar item: " + e.getMessage();
        }
    }

    public String pegarItem(Save save, String item) {
        if (inventario.contains(item.toUpperCase())) {
            return "Você já possui " + item.toUpperCase() + " no seu inventário.";
        }
    
        switch (save.getIdCenaAtual()) {
            case 1:
                // Itens disponíveis na cena 1
                if (item.equalsIgnoreCase("LANTERNA") || item.equalsIgnoreCase("CHAVE DE FERRO")) {
                    inventario.add(item.toUpperCase());
                    return "Você pegou " + item.toUpperCase() + ".";
                }
                break;
            case 2:
                // Itens disponíveis na cena 2
                if (item.equalsIgnoreCase("CHAVE DE FERRO")) {
                    inventario.add(item.toUpperCase());
                    return "Você pegou " + item.toUpperCase() + ".";
                }
                break;
            case 3:
                // Itens disponíveis na cena 3
                if (item.equalsIgnoreCase("DIÁRIO ANTIGO") || item.equalsIgnoreCase("ESPADA DE PRATA")) {
                    inventario.add(item.toUpperCase());
                    return "Você pegou " + item.toUpperCase() + ".";
                }
                break;
            // Adicione outras cenas conforme necessário
            default:
                return "Item não disponível para coleta nesta cena.";
        }
    
        return "Item não disponível para coleta nesta cena.";
    }
    

    private String mostrarInventario() {
        if (inventario.isEmpty()) {
            return "Seu inventário está vazio.";
        }
        return "Itens no inventário: " + String.join(", ", inventario) + ".";
    }

    private String help() {
        return "Comandos disponíveis: help, start, restart, use <item>, get <item>, inventory.";
    }
}
