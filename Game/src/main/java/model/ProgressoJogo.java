package model;

public class ProgressoJogo {
    private int id;
    private int idSave;
    private int cenaAtual;
    private int cenaDestino;

    public ProgressoJogo() {}

    public ProgressoJogo(int id, int idSave, int cenaAtual, int cenaDestino) {
        this.id = id;
        this.idSave = idSave;
        this.cenaAtual = cenaAtual;
        this.cenaDestino = cenaDestino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSave() {
        return idSave;
    }

    public void setIdSave(int idSave) {
        this.idSave = idSave;
    }

    public int getCenaAtual() {
        return cenaAtual;
    }

    public void setCenaAtual(int cenaAtual) {
        this.cenaAtual = cenaAtual;
    }

    public int getCenaDestino() {
        return cenaDestino;
    }

    public void setCenaDestino(int cenaDestino) {
        this.cenaDestino = cenaDestino;
    }

    @Override
    public String toString() {
        return "ProgressoJogo{" +
               "id=" + id +
               ", idSave=" + idSave +
               ", cenaAtual=" + cenaAtual +
               ", cenaDestino=" + cenaDestino +
               '}';
    }
}
