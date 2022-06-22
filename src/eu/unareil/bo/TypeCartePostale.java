package eu.unareil.bo;

public enum TypeCartePostale {
    Paysage("Paysage");
    private String libelle;
    TypeCartePostale(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
}
