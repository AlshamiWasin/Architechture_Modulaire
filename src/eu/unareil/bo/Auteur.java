package eu.unareil.bo;

public class Auteur {
    private long id;
    private String prenom;
    private String nom;
    public Auteur() {
    }
    public Auteur(String prenom, String nom) {
        this.prenom = prenom;
        this.nom = nom;
    }
    public Auteur(long id, String prenom, String nom) {
        this(prenom,nom);
        this.setId(id);
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Auteur{");
        sb.append("id=").append(id);
        sb.append(", prenom='").append(prenom).append('\'');
        sb.append(", nom='").append(nom).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
