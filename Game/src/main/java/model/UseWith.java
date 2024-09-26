package model;

public class UseWith {
    private int id;
    private int idItemInventario;
    private int idItemCena;
    private String descricaoAcao;

    public UseWith(int id, int idItemInventario, int idItemCena, String descricaoAcao) {
        this.id = id;
        this.idItemInventario = idItemInventario;
        this.idItemCena = idItemCena;
        this.descricaoAcao = descricaoAcao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdItemInventario() {
        return idItemInventario;
    }

    public void setIdItemInventario(int idItemInventario) {
        this.idItemInventario = idItemInventario;
    }

    public int getIdItemCena() {
        return idItemCena;
    }

    public void setIdItemCena(int idItemCena) {
        this.idItemCena = idItemCena;
    }

    public String getDescricaoAcao() {
        return descricaoAcao;
    }

    public void setDescricaoAcao(String descricaoAcao) {
        this.descricaoAcao = descricaoAcao;
    }
}
