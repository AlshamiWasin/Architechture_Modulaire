package eu.unareil.bo;

import java.util.ArrayList;
import java.util.List;

public class CartePostale extends Produit{

    private List<Auteur> lesAuteurs = new ArrayList<>();
    private TypeCartePostale type;

    public CartePostale() {
        super();
    }

    public CartePostale(long refProd, String marque, String libelle, long qteStock, float prixUnitaire, List<Auteur> lesAuteurs, TypeCartePostale type) {
        super(refProd, marque, libelle, qteStock, prixUnitaire);
        this.lesAuteurs = lesAuteurs;
        this.type = type;
    }

    public CartePostale(String marque, String libelle, long qteStock, float prixUnitaire, List<Auteur> lesAuteurs, TypeCartePostale type) {
        this(0, marque, libelle, qteStock, prixUnitaire,lesAuteurs,type);
    }




    public List<Auteur> getLesAuteurs() {
        return lesAuteurs;
    }

    public void setLesAuteurs(List<Auteur> lesAuteurs) {
        this.lesAuteurs = lesAuteurs;
    }

    public TypeCartePostale getType() {
        return type;
    }

    public void setType(TypeCartePostale type) {
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CartePostale [");
        sb.append("libelle=").append(getLibelle());
        sb.append(", marque=").append(getMarque());
        sb.append(", prixUnitaire=").append(getPrixUnitaire());
        sb.append(", qteStock=").append(getQteStock());
        sb.append(", auteur(s)=");

        for (Auteur auteur : lesAuteurs) {
            sb.append("auteur").append(lesAuteurs.indexOf(auteur)+1).append("=").append(auteur.getPrenom()).append(" ").append(auteur.getNom()).append(", ");
        }

        sb.append(", type=").append(type);
        sb.append(']');
        return sb.toString();
    }
}
