package model;

import java.util.List;

public class Cena {
    private Integer idCena;
    private String descricao;
    private List<Item> itens_da_cena;

    public Integer getIdCena() {
        return idCena;
    }

    public void setIdCena(Integer idCena) {
        this.idCena = idCena;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public List<Item> getItens() {
        return itens_da_cena;
    }

    public void setItens(List<Item> itens) {
        this.itens_da_cena = itens;
    }

    @Override
    public String toString() {
        return "Cena{" +
                "idCena=" + idCena +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
