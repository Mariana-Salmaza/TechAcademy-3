package model;

public class Inventario {
    private Integer idInventario;
    private Integer idSave;
    private String nomeJogador;
    private String nomeItem;
    private String descricao;

    public Integer getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(Integer idInventario) {
        this.idInventario = idInventario;
    }

    public Integer getIdSave() {
        return idSave;
    }

    public void setIdSave(Integer idSave) {
        this.idSave = idSave;
    }

    public String getNomeJogador() {
        return nomeJogador;
    }

    public void setNomeJogador(String nomeJogador) {
        this.nomeJogador = nomeJogador;
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
        return "Inventario{" +
                "idInventario=" + idInventario +
                ", idSave=" + idSave +
                ", nomeJogador='" + nomeJogador + '\'' +
                ", nomeItem='" + nomeItem + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
