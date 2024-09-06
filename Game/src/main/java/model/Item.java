package model;

public class Item {
    
    private Integer idItem;
    private Cena id_Cena; 
    private String nome;
    private String descricaoPositiva;
    private String descricaoNegativa;
    private String comandoCorreto;
    private Boolean interagivel;

    public Integer getIdItem() {
        return idItem;
    }
    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }
    public Cena getId_Cena() {  
        return id_Cena;
    }
    public void setId_Cena(Cena id_Cena) {
        this.id_Cena = id_Cena;
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
    public Boolean getInteragivel() {
        return interagivel;
    }
    public void setInteragivel(Boolean interagivel) {
        this.interagivel = interagivel;
    }

    @Override
    public String toString() {
        return "Item{" +
                "idItem=" + idItem +
                ", id_Cena=" + id_Cena + 
                ", nome='" + nome + '\'' +
                ", descricaoPositiva='" + descricaoPositiva + '\'' +
                ", descricaoNegativa='" + descricaoNegativa + '\'' +
                ", comandoCorreto='" + comandoCorreto + '\'' +
                ", interagivel=" + interagivel +  
                '}';
    }
}
