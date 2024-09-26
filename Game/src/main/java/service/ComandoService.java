package service;

import model.Cena;
import model.ItemDaCena;
import model.ItemInventario;
import repository.CenaDAO;
import repository.ItemDaCenaDAO;
import repository.ItemInventarioDAO;
import repository.SaveDAO;
import java.sql.SQLException;
import java.util.List;

public class ComandoService {
    private int idCenaAtual;
    private int idSave;
    private ItemInventarioDAO itemInventarioDAO;
    private ItemDaCenaDAO itemDaCenaDAO;
    private CenaDAO cenaDAO;
    private SaveDAO saveDAO;

    public ComandoService(ItemInventarioDAO itemInventarioDAO, ItemDaCenaDAO itemDaCenaDAO, CenaDAO cenaDAO,SaveDAO saveDAO) {
        this.itemInventarioDAO = itemInventarioDAO;
        this.itemDaCenaDAO = itemDaCenaDAO;
        this.cenaDAO = cenaDAO;
        this.saveDAO = saveDAO;
        this.idCenaAtual = 1; // Começa na cena 1 por padrão
    }

    // Método para iniciar o jogo
    public void start() throws SQLException {
        // Carregar a cena inicial
        Cena cenaInicial = cenaDAO.getCenaInicial(); // Método para obter a cena inicial
        if (cenaInicial != null) {
            idCenaAtual = cenaInicial.getId(); // Definindo o idCenaAtual com a cena inicial
            System.out.println("Bem-vindo ao jogo!");
            System.out.println("Você está na cena: " + cenaInicial.getDescricao());
            System.out.println(cenaInicial.getDescricao()); // Exibindo a descrição da cena
            exibirCenaAtual(); // Exibe a cena inicial
        } else {
            System.out.println("Erro ao carregar a cena inicial.");
        }
    }

    // Exibe a cena atual
    private void exibirCenaAtual() {
        try {
            Cena cena = cenaDAO.findCenaById(idCenaAtual);
            if (cena != null) {
                System.out.println("Cena: " + cena.getDescricao());
                listarItensDaCena();
            } else {
                System.out.println("Cena não encontrada.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar cena: " + e.getMessage());
        }
    }

    // Lista os itens da cena atual
    private void listarItensDaCena() {
        try {
            List<ItemDaCena> itens = itemDaCenaDAO.listarItensDaCenaPorId(idCenaAtual);
            if (!itens.isEmpty()) {
                System.out.println("Itens disponíveis na cena:");
                for (ItemDaCena item : itens) {
                    System.out.println("- " + item.getNome());
                }
            } else {
                System.out.println("Não há itens nesta cena.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar itens da cena: " + e.getMessage());
        }
    }

    // HELP
    public String help() {
        // Retorna uma string com a lista de comandos
        return "Comandos disponíveis:\n" +
                "/start - Inicia o jogo.\n" +
                "/help - Exibe esta lista de comandos.\n" +
                "/use [ITEM] - Usa um item do inventário.\n" +
                "/get [ITEM] - Pega um item da cena.\n" +
                "/usewith [ITEM_INVENTARIO] [ITEM_CENA] - Usa um item do inventário com um item da cena.\n" +
                "/inventory - Exibe os itens no inventário.\n" +
                "/restart - Reinicia o jogo.\n";
    }

    // Pega um item da cena e adiciona ao inventário
    public void pegarItem(String itemNome) {
        try {
            List<ItemDaCena> itensCena = itemDaCenaDAO.listarItensDaCenaPorId(idCenaAtual);
            for (ItemDaCena itemCena : itensCena) {
                if (itemCena.getNome().equalsIgnoreCase(itemNome)) {
                    itemInventarioDAO.adicionarItem(new ItemInventario(
                            itemCena.getIdItem(),
                            itemCena.getNome(),
                            itemCena.getDescricaoPositiva(),
                            itemCena.getDescricaoNegativa(),
                            idSave));
                    System.out.println("Você pegou o item: " + itemCena.getNome());
                    return;
                }
            }
            System.out.println("Item não encontrado na cena.");
        } catch (SQLException e) {
            System.out.println("Erro ao pegar item da cena: " + e.getMessage());
        }
    }

    // Usa um item do inventário
    public void usarItem(String itemNome) {
        try {
            ItemInventario itemInventario = itemInventarioDAO.buscarPorNome(itemNome);
            if (itemInventario != null) {
                List<ItemDaCena> itensCena = itemDaCenaDAO.listarItensDaCenaPorId(idCenaAtual);
                boolean itemUsado = false;

                for (ItemDaCena itemCena : itensCena) {
                    if (itemCena.getNome().equalsIgnoreCase(itemNome)) {
                        System.out.println(itemCena.getDescricaoPositiva());
                        itemUsado = true;
                        break;
                    }
                }

                if (!itemUsado) {
                    System.out.println(itemInventario.getDescricaoNegativa());
                }
            } else {
                System.out.println("Item não encontrado no inventário.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao usar item: " + e.getMessage());
        }
    }

    // Usa um item do inventário com um item da cena
    public void usarCom(String itemInventarioNome, String itemCenaNome) {
        try {
            ItemInventario itemInventario = itemInventarioDAO.buscarPorNome(itemInventarioNome);
            if (itemInventario == null) {
                System.out.println("Item do inventário não encontrado.");
                return;
            }

            List<ItemDaCena> itensCena = itemDaCenaDAO.listarItensDaCenaPorId(idCenaAtual);
            if (itensCena.isEmpty()) {
                System.out.println("Nenhum item encontrado na cena.");
                return;
            }

            for (ItemDaCena itemCena : itensCena) {
                if (itemCena.getNome().equalsIgnoreCase(itemCenaNome)) {
                    if (itemDaCenaDAO.verificarUso(itemInventario.getId(), itemCena.getIdItem())) {
                        System.out.println("Você usou " + itemInventario.getNome() + " com " + itemCena.getNome());
                        aplicarEfeitos(itemInventario, itemCena);
                        return;
                    } else {
                        System.out.println("Esses itens não podem ser usados juntos.");
                        return;
                    }
                }
            }
            System.out.println("Item da cena não encontrado.");
        } catch (SQLException e) {
            System.out.println("Erro ao usar itens juntos: " + e.getMessage());
        }
    }

    // Aplica os efeitos da interação entre itens
    private void aplicarEfeitos(ItemInventario itemInventario, ItemDaCena itemCena) {
        String efeito = "";
        int novaCenaId = -1;

        if (itemInventario.getNome().equalsIgnoreCase("LANTERNA")
                && itemCena.getNome().equalsIgnoreCase("PORTA ESCURA")) {
            efeito = "A lanterna ilumina a porta escura, revelando um caminho.";
            novaCenaId = 2;
        } else if (itemInventario.getNome().equalsIgnoreCase("CHAVE") && itemCena.getNome().equalsIgnoreCase("COFRE")) {
            efeito = "Você usa a chave para abrir o cofre e encontra um tesouro.";
            novaCenaId = 3;
        }

        System.out.println(efeito);

        if (novaCenaId != -1) {
            mudarCena(novaCenaId);
        }
    }

    // Muda para uma nova cena
    private void mudarCena(int novaCenaId) {
        idCenaAtual = novaCenaId;
        exibirCenaAtual();
    }

    // Exibe o inventário do jogador
    public void mostrarInventario() {
        try {
            List<ItemInventario> inventario = itemInventarioDAO.listarItensPorSave(idSave);
            if (inventario.isEmpty()) {
                System.out.println("Seu inventário está vazio.");
            } else {
                System.out.println("Itens no seu inventário:");
                for (ItemInventario item : inventario) {
                    System.out.println("- " + item.getNome());
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao exibir inventário: " + e.getMessage());
        }
    }

    // RESTART
    public void restart() {
        idCenaAtual = 1; // Ou outra lógica para reiniciar o jogo
        System.out.println("O jogo foi reiniciado.");
        exibirCenaAtual();
    }
}
