package model;

public class Inventario {
    private Integer idInventario;
    private String nomeItem;
    private String descricao;

    public Integer getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Inventario{"+
                "idInventario=" + idInventario +
                ", nomeItem=" + nomeItem +
                ", descricao=" + descricao +
                '}';
    }
}
