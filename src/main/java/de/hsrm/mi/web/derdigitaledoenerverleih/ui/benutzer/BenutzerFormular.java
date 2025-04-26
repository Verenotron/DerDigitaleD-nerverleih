package de.hsrm.mi.web.derdigitaledoenerverleih.ui.benutzer;

public class BenutzerFormular {

    //Properties -> private Instalsvariablen mit Getter Setter
    private String name = "";
    private String anschrift = "";
    private int vegetarizitaet = 0;
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
    public int getVegetarizitaet() {
        return vegetarizitaet;
    }
    public void setVegetarizitaet(int vegetarizitaet) {
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

    
    
}
