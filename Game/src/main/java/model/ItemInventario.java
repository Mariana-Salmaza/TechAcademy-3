package model;

import java.util.Objects;

public class ItemInventario {
    private Integer id;
    private String nome;
    private String descricaoPositiva;
    private String descricaoNegativa; 
    private Integer idSave;

    public ItemInventario(Integer id, String nome, String descricaoPositiva, String descricaoNegativa, Integer idSave) {
        this(id, nome, descricaoPositiva, descricaoNegativa);
        this.idSave = idSave;
    }

    public ItemInventario(Integer id, String nome, String descricaoPositiva, String descricaoNegativa) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser nulo ou vazio.");
        }
        if (descricaoPositiva == null || descricaoPositiva.isEmpty()) {
            throw new IllegalArgumentException("A descrição positiva não pode ser nula ou vazia.");
        }
        this.id = id;
        this.nome = nome;
        this.descricaoPositiva = descricaoPositiva;
        this.descricaoNegativa = descricaoNegativa;
    }

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
        return String.format("ItemInventario{id=%d, nome='%s', descricaoPositiva='%s', descricaoNegativa='%s', idSave=%d}",
                id, nome, descricaoPositiva, descricaoNegativa, idSave);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemInventario)) return false;
        ItemInventario that = (ItemInventario) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
