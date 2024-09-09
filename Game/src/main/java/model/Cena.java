package model;

import java.util.List;

public class Cena {
    private Integer id;
    private String descricao;
    private List<Item> itens;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", itens=" + itens +
                '}';
    }

    public int getIdCena() {
        throw new UnsupportedOperationException("Unimplemented method 'getIdCena'");
    }
}
