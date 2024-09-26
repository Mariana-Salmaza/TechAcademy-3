package model;

public class Save {
    private int id;
    private int idCenaAtual; // Referência direta à cena atual como ID (inteiro)

    public Save() {} // Construtor padrão

    public Save(int id, int idCenaAtual) {
        this.id = id;
        this.idCenaAtual = idCenaAtual;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCenaAtual() {
        return idCenaAtual;
    }

    public void setIdCenaAtual(int idCenaAtual) {
        this.idCenaAtual = idCenaAtual;
    }

    @Override
    public String toString() {
        return "Save{" +
                "id=" + id +
                ", idCenaAtual=" + idCenaAtual +
                '}';
    }
}
