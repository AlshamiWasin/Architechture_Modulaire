package eu.unareil.bo;

import java.time.LocalDate;

public class Pain extends ProduitPerissable{

    private long id;
    private int poids;

    public Pain() {
        super();
    }


    public Pain(long refProd, LocalDate datelimitConso, String marque, String libelle, long qteStock, float prixUnitaire, long id, int poids) {
        super(refProd, datelimitConso, marque, libelle, qteStock, prixUnitaire);
        this.id = id;
        this.poids = poids;
    }

    public Pain(String marque, String libelle, long qteStock,  int poids, float prixUnitaire) {
        super(LocalDate.now().plusDays(2), marque, libelle, qteStock, prixUnitaire);
        this.poids = poids;
    }

}
