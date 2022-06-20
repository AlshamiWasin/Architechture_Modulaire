package eu.unareil.bo;

import java.time.LocalDate;

public class Pain extends ProduitPerissable{

    private int poids;

    public Pain() {
        super();
    }

    public Pain(long refProd ,String marque, String libelle,int poids, long qteStock, float prixUnitaire) {
        super(refProd, LocalDate.now().plusDays(2), marque, libelle, qteStock, prixUnitaire);
        this.setPoids(poids);
    }

    public Pain(String marque, String libelle, long qteStock,  int poids, float prixUnitaire) {
        this(0,marque, libelle,poids,qteStock,prixUnitaire);
    }


    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CartePostale [");
        sb.append("libelle=").append(getLibelle());
        sb.append(", marque=").append(getMarque());
        sb.append(", prixUnitaire=").append(getPrixUnitaire());
        sb.append(", qteStock=").append(getQteStock());
        sb.append(", dateLimiteConso=").append(getDatelimitConso());
        sb.append(", poids=").append(getPoids());
        sb.append(']');
        return sb.toString();
    }
}

