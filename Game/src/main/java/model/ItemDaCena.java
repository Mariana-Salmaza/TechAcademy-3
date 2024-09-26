package model;

import java.util.Objects;

public class ItemDaCena {
    private Integer id;
    private String nome;
    private String descricaoPositiva;
    private String descricaoNegativa;
    private String comandoCorreto;
    private Integer idCenaAtual;
    private Integer idCenaDestino;
    private Boolean interagivel;

    public ItemDaCena(Integer id, String nome, String descricaoPositiva, String descricaoNegativa, 
                      String comandoCorreto, Integer idCenaAtual, Integer idCenaDestino, 
                      Boolean interagivel) {
        this.id = id;
        this.nome = nome;
        this.descricaoPositiva = descricaoPositiva;
        this.descricaoNegativa = descricaoNegativa;
        this.comandoCorreto = comandoCorreto;
        this.idCenaAtual = idCenaAtual;
        this.idCenaDestino = idCenaDestino;
        this.interagivel = interagivel;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricaoPositiva() { return descricaoPositiva; }
    public void setDescricaoPositiva(String descricaoPositiva) { this.descricaoPositiva = descricaoPositiva; }

    public String getDescricaoNegativa() { return descricaoNegativa; }
    public void setDescricaoNegativa(String descricaoNegativa) { this.descricaoNegativa = descricaoNegativa; }

    public String getComandoCorreto() { return comandoCorreto; }
    public void setComandoCorreto(String comandoCorreto) { this.comandoCorreto = comandoCorreto; }

    public Integer getIdCenaAtual() { return idCenaAtual; }
    public void setIdCenaAtual(Integer idCenaAtual) { this.idCenaAtual = idCenaAtual; }

    public Integer getIdCenaDestino() { return idCenaDestino; }
    public void setIdCenaDestino(Integer idCenaDestino) { this.idCenaDestino = idCenaDestino; }

    public Boolean getInteragivel() { return interagivel; }
    public void setInteragivel(Boolean interagivel) { this.interagivel = interagivel; }

    @Override
    public String toString() {
        return "ItemDaCena{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricaoPositiva='" + descricaoPositiva + '\'' +
                ", descricaoNegativa='" + descricaoNegativa + '\'' +
                ", comandoCorreto='" + comandoCorreto + '\'' +
                ", idCenaAtual=" + idCenaAtual +
                ", idCenaDestino=" + idCenaDestino +
                ", interagivel=" + interagivel +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemDaCena)) return false;
        ItemDaCena that = (ItemDaCena) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
