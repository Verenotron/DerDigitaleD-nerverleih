package de.hsrm.mi.web.derdigitaledoenerverleih.ui.doener;

import java.util.ArrayList;
import java.util.List;

import de.hsrm.mi.web.derdigitaledoenerverleih.entities.zutat.Zutat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class DoenerFormular {

    private String[] ernaerungsAuswahl;

    private long id;
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
    
    private List<Zutat> zutaten = new ArrayList<Zutat>();

    public DoenerFormular(){
        this.ernaerungsAuswahl = new String[]{"vegan", "vegetarisch", "alles"};
        this.bezeichnung = "";
        this.preis = 0;
        this.bestand = 0;
    }

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

    public String[] getErnaerungsAuswahl() {
        return ernaerungsAuswahl;
    }

    public void setErnaerungsAuswahl(String[] ernaerungsAuswahl) {
        this.ernaerungsAuswahl = ernaerungsAuswahl;
    }
    
    
    
}
