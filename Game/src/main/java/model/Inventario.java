package model;

public class Inventario {
    private Integer idInventario;
    private String nomeItem;
    private String descricao;
    private Integer id_save;
    
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
    public Integer getId_save() {
        return id_save;
    }
    public void setId_save(Integer id_save) {
        this.id_save = id_save;
    }

    @Override
    public String toString() {
        return "Inventario [idInventario=" + idInventario + ", nomeItem=" + nomeItem + ", descricao=" + descricao
                + ", id_save=" + id_save + "]";
    }
    public Object getQuantidade() {
        throw new UnsupportedOperationException("Unimplemented method 'getQuantidade'");
    }
}
