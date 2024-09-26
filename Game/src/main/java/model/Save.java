package model;

public class Save {
    private int id;
    private int idCenaAtual;

    public Save() {}

    public Save(int id, int idCenaAtual) {
        this.id = id;
        this.idCenaAtual = idCenaAtual;
    }

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
