package model;

public class ItemInventario {
    private Integer id;
    private String nome;
    private String descricaoPositiva; // Para a descrição positiva do item
    private String descricaoNegativa; // Para a descrição negativa do item
    private Integer idSave; // Relaciona o item a um save específico

    public ItemInventario(Integer id, String nome, String descricaoPositiva, String descricaoNegativa, Integer idSave) {
        this.id = id;
        this.nome = nome;
        this.descricaoPositiva = descricaoPositiva;
        this.descricaoNegativa = descricaoNegativa;
        this.idSave = idSave;
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getIdSave() {
        return idSave;
    }

    public void setIdSave(Integer idSave) {
        this.idSave = idSave;
    }

    @Override
    public String toString() {
        return "ItemInventario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricaoPositiva='" + descricaoPositiva + '\'' +
                ", descricaoNegativa='" + descricaoNegativa + '\'' +
                ", idSave=" + idSave +
                '}';
    }
}
