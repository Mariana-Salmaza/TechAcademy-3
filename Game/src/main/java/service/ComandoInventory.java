package service;

import model.Console;
import model.Inventario;
import repository.InventarioDAO;

import java.util.List;

public class ComandoInventory {
    private Console console;

    public ComandoInventory() {
        this.console = new Console();
    }

    public Console executar() {
        try {
            List<Inventario> inventarios = InventarioDAO.findAllInventarios();
            if (inventarios.isEmpty()) {
                console.setMensagem("Seu inventário está vazio.");
            } else {
                StringBuilder mensagem = new StringBuilder("Itens no seu inventário:\n");
                for (Inventario inventario : inventarios) {
                    mensagem.append("Item: ").append(inventario.getNomeItem())
                            .append(" - Quantidade: ").append(inventario.getQuantidade())
                            .append("\n");
                }
                console.setMensagem(mensagem.toString());
            }
        } catch (Exception e) {
            console.setMensagem("Erro ao buscar os itens do inventário: " + e.getMessage());
        }
        return console;
    }
}
