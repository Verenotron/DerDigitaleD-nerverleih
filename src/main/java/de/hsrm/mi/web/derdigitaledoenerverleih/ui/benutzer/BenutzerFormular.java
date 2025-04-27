package de.hsrm.mi.web.derdigitaledoenerverleih.ui.benutzer;

public class BenutzerFormular {

    private String[] ernaerungsAuswahl = {"vegan", "vegetarisch", "alles"};
    private String[] rollen = {"ADMIN", "KUNDE", "GESPERRT"};

    //Properties -> private Instalsvariablen mit Getter Setter
    private String name = "";
    private String anschrift = "";
    private String vegetarizitaet = "0";
    private String rolle = "";
    private String losung = "";
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAnschrift() {
        return anschrift;
    }
    public void setAnschrift(String anschrift) {
        this.anschrift = anschrift;
    }
    public String getVegetarizitaet() {
        return vegetarizitaet;
    }
    public void setVegetarizitaet(String vegetarizitaet) {
        this.vegetarizitaet = vegetarizitaet;
    }
    public String getRolle() {
        return rolle;
    }
    public void setRolle(String rolle) {
        this.rolle = rolle;
    }
    public String getLosung() {
        return losung;
    }
    public void setLosung(String losung) {
        this.losung = losung;
    }
    public String[] getErnaerungsAuswahl() {
        return ernaerungsAuswahl;
    }
    public String[] getRollen() {
        return rollen;
    }

    
    
}
