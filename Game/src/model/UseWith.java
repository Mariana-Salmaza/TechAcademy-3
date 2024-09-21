package model;

public class UseWith {
    private Integer id;
    private Integer idItemInventario;
    private Integer idItemCena;
    private String descricaoAcao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdItemInventario() {
        return idItemInventario;
    }

    public void setIdItemInventario(Integer idItemInventario) {
        this.idItemInventario = idItemInventario;
    }

    public Integer getIdItemCena() {
        return idItemCena;
    }

    public void setIdItemCena(Integer idItemCena) {
        this.idItemCena = idItemCena;
    }

    public String getDescricaoAcao() {
        return descricaoAcao;
    }

    public void setDescricaoAcao(String descricaoAcao) {
        this.descricaoAcao = descricaoAcao;
    }

    @Override
    public String toString() {
        return "UseWith{" +
                "id=" + id +
                ", idItemInventario=" + idItemInventario +
                ", idItemCena=" + idItemCena +
                ", descricaoAcao='" + descricaoAcao + '\'' +
                '}';
    }
}
