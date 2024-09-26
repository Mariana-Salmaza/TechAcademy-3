package model;

public class UseWith {
    private int idItemInventario;
    private int idItemCena;

    public UseWith() {}

    public UseWith(int idItemInventario, int idItemCena) {
        if (idItemInventario <= 0 || idItemCena <= 0) {
            throw new IllegalArgumentException("Os IDs dos itens devem ser maiores que zero.");
        }
        this.idItemInventario = idItemInventario;
        this.idItemCena = idItemCena;
    }

    public int getIdItemInventario() {
        return idItemInventario;
    }

    public void setIdItemInventario(int idItemInventario) {
        if (idItemInventario <= 0) {
            throw new IllegalArgumentException("O ID do item do inventário deve ser maior que zero.");
        }
        this.idItemInventario = idItemInventario;
    }

    public int getIdItemCena() {
        return idItemCena;
    }

    public void setIdItemCena(int idItemCena) {
        if (idItemCena <= 0) {
            throw new IllegalArgumentException("O ID do item da cena deve ser maior que zero.");
        }
        this.idItemCena = idItemCena;
    }
}
