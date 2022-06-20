package eu.unareil.bo;

import java.util.ArrayList;
import java.util.List;

public class CartePostale extends Produit{

    private long id;
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

    public CartePostale(long refProd, String marque, String libelle, long qteStock, float prixUnitaire,long id, List<Auteur> lesAuteurs, TypeCartePostale type) {
        this(refProd, marque, libelle, qteStock, prixUnitaire,lesAuteurs,type);
        this.setId(id);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Auteur> getLesAuteurs() {
        return lesAuteurs;
    }

    public void setLesAuteurs(List<Auteur> lesAuteurs) {
        this.lesAuteurs = lesAuteurs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CartePostale{");
        sb.append("lesAuteurs=").append(lesAuteurs);
        sb.append(", type='").append(type).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
