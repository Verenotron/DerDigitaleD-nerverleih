package de.hsrm.mi.web.derdigitaledoenerverleih.entities.doener;

import java.util.ArrayList;
import java.util.List;

import de.hsrm.mi.web.derdigitaledoenerverleih.entities.zutat.Zutat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Doener {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Version
    private long version;
    @NotNull
    @Size(min=3, max=40, message="Name muss min 3 und max 40 Zeichen lang sein.")
    private String bezeichnung;
    @Positive
    private int preis;
    @NotNull
    private int vegetarizitaet;
    @Positive
    private int bestand;
    int entliehen = 0;
    
    @ManyToMany
    private List<Zutat> zutaten = new ArrayList<Zutat>();

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getVersion() {
        return version;
    }
    public void setVersion(long version) {
        this.version = version;
    }
    public String getBezeichnung() {
        return bezeichnung;
    }
    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }
    public int getPreis() {
        return preis;
    }
    public void setPreis(int preis) {
        this.preis = preis;
    }
    public int getVegetarizitaet() {
        return vegetarizitaet;
    }
    public void setVegetarizitaet(int vegetarizitaet) {
        this.vegetarizitaet = vegetarizitaet;
    }
    public int getBestand() {
        return bestand;
    }
    public void setBestand(int bestand) {
        this.bestand = bestand;
    }
    public List<Zutat> getZutaten() {
        return zutaten;
    }
    public void setZutaten(List<Zutat> zutaten) {
        this.zutaten = zutaten;
    }
    public int getVerfuegbar(){
        return bestand - entliehen;
    }
    
    
}
