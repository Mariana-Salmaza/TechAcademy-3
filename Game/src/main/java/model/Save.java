package model;

public class Save {
    private Integer idSave;
    private Cena cenaAtual;

    public Save() {}

    public Save(Integer idSave, Cena cenaAtual) {
        this.idSave = idSave;
        this.cenaAtual = cenaAtual;
    }

    public Integer getIdSave() {
        return idSave;
    }

    public void setIdSave(Integer idSave) {
        this.idSave = idSave;
    }

    public Cena getCenaAtual() {
        return cenaAtual;
    }

    public void setCenaAtual(Cena cenaAtual) {
        this.cenaAtual = cenaAtual;
    }

    @Override
    public String toString() {
        return "Save{" +
                "idSave=" + idSave +
                ", cenaAtual=" + cenaAtual +
                '}';
    }
}
