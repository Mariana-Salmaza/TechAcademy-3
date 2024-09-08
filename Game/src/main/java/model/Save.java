package model;

public class Save {
    private Integer idSave;
    private Integer idJogador;
    private Cena cenaAtual;

    public Integer getIdSave() {
        return idSave;
    }

    public void setIdSave(Integer idSave) {
        this.idSave = idSave;
    }

    public Integer getIdJogador() {
        return idJogador;
    }

    public void setIdJogador(Integer idJogador) {
        this.idJogador = idJogador;
    }

    public Cena getCenaAtual() {
        return cenaAtual;
    }

    public void setCenaAtual(Cena cenaAtual) {
        this.cenaAtual = cenaAtual;
    }

    @Override
    public String toString() {
        return "Save [idSave=" + idSave + ", idJogador=" + idJogador + ", cenaAtual=" + cenaAtual + "]";
    }
}
