package model;

public class ItemDaCena {
    private int idItem;
    private String nome;
    private String descricaoPositiva;
    private String descricaoNegativa;
    private String comandoCorreto;
    private int idCenaAtual; 
    private int idCenaDestino; 
    private boolean interagivel; 

    public ItemDaCena(int idItem, String nome, String descricaoPositiva, String descricaoNegativa,String comandoCorreto, int idCenaAtual, int idCenaDestino, boolean interagivel) {
        this.idItem = idItem;
        this.nome = nome;
        this.descricaoPositiva = descricaoPositiva;
        this.descricaoNegativa = descricaoNegativa;
        this.comandoCorreto = comandoCorreto;
        this.idCenaAtual = idCenaAtual;
        this.idCenaDestino = idCenaDestino;
        this.interagivel = interagivel;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricaoPositiva() {
        return descricaoPositiva;
    }

    public void setDescricaoPositiva(String descricaoPositiva) {
        this.descricaoPositiva = descricaoPositiva;
    }

    public String getDescricaoNegativa() {
        return descricaoNegativa;
    }

    public void setDescricaoNegativa(String descricaoNegativa) {
        this.descricaoNegativa = descricaoNegativa;
    }

    public String getComandoCorreto() {
        return comandoCorreto;
    }

    public void setComandoCorreto(String comandoCorreto) {
        this.comandoCorreto = comandoCorreto;
    }

    public int getIdCenaAtual() {
        return idCenaAtual;
    }

    public void setIdCenaAtual(int idCenaAtual) {
        this.idCenaAtual = idCenaAtual;
    }

    public int getIdCenaDestino() {
        return idCenaDestino;
    }

    public void setIdCenaDestino(int idCenaDestino) {
        this.idCenaDestino = idCenaDestino;
    }

    public boolean isInteragivel() {
        return interagivel;
    }

    public void setInteragivel(boolean interagivel) {
        this.interagivel = interagivel;
    }

    @Override
    public String toString() {
        return "ItemDaCena{" +
                "idItem=" + idItem +
                ", nome='" + nome + '\'' +
                ", descricaoPositiva='" + descricaoPositiva + '\'' +
                ", descricaoNegativa='" + descricaoNegativa + '\'' +
                ", comandoCorreto='" + comandoCorreto + '\'' +
                ", idCenaAtual=" + idCenaAtual +
                ", idCenaDestino=" + idCenaDestino +
                ", interagivel=" + interagivel +
                '}';
    }
}
