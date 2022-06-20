package eu.unareil.bo;

import java.time.LocalDate;

public class Glace extends ProduitPerissable{

    private long id;
    private String parfum;
    private int temperatureConservation;

    public Glace() {
    }


    public Glace(LocalDate datelimitConso, String marque, String libelle, int temperatureConservation, String parfum, long qteStock, float prixUnitaire) {
        super(datelimitConso, marque, libelle, qteStock, prixUnitaire);
        this.parfum = parfum;
        this.temperatureConservation = temperatureConservation;
    }


    public Glace(long id ,LocalDate datelimitConso, String marque, String libelle, int temperatureConservation, String parfum, long qteStock, float prixUnitaire) {
        this(datelimitConso, marque, libelle,temperatureConservation,parfum, qteStock, prixUnitaire);
        this.setId(id);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getParfum() {
        return parfum;
    }

    public void setParfum(String parfum) {
        this.parfum = parfum;
    }

    public int getTemperatureConservation() {
        return temperatureConservation;
    }

    public void setTemperatureConservation(int temperatureConservation) {
        this.temperatureConservation = temperatureConservation;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Glace{");
        sb.append("id=").append(id);
        sb.append(", parfum='").append(parfum).append('\'');
        sb.append(", temperatureConservation=").append(temperatureConservation);
        sb.append('}');
        return sb.toString();
    }
}
