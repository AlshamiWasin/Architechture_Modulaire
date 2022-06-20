package eu.unareil.bo;

public class Stylo extends Produit{

    private long id;
    private String couleur;
    private String typeMine;

    public Stylo() {
        super();
    }

    public Stylo(String marque, String libelle, long qteStock, float prixUnitaire, String couleur, String typeMine) {
        super(marque ,libelle,qteStock, prixUnitaire );
        this.couleur = couleur;
        this.typeMine = typeMine;
    }

    public Stylo(String marque, String  libelle, long qteStock, float prixUnitaire, long id, String couleur, String typeMine) {
        this(marque ,libelle ,  qteStock , prixUnitaire,couleur,typeMine);
        this.setId(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getTypeMine() {
        return typeMine;
    }

    public void setTypeMine(String typeMine) {
        this.typeMine = typeMine;
    }
}
