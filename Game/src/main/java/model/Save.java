package model;

public class Save {
    private Integer idSave;

    public Save(Integer idSave) {
        this.idSave = idSave;
    }

    public Integer getIdSave() {
        return idSave;
    }

    public void setIdSave(Integer idSave) {
        this.idSave = idSave;
    }

    @Override
    public String toString() {
        return "Save{" +
                "idSave=" + idSave +
                '}';
    }
}
