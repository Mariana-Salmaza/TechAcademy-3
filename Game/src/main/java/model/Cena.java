package model;

public class Cena {
    private int id;
    private String descricao; // Mantém apenas a descrição

    // Construtor
    public Cena(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    // Construtor padrão
    public Cena() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
