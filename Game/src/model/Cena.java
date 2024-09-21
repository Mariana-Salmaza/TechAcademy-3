package model;

import java.util.List;

public class Cena {
    private Integer idCena;
    private String descricao;
    private List<Item> itens;

    public Integer getIdCena() {
        return idCena;
    }

    public void setIdCena(Integer id) {
        this.idCena = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "Cena{" +
                "id=" + idCena +
                ", descricao='" + descricao + '\'' +
                ", itens=" + itens +
                '}';
    }
}