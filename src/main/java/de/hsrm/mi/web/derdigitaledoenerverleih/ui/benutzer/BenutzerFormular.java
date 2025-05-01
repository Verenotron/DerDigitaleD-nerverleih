package de.hsrm.mi.web.derdigitaledoenerverleih.ui.benutzer;

import de.hsrm.mi.web.derdigitaledoenerverleih.ui.validators.Losung;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class BenutzerFormular {

    private String[] ernaerungsAuswahl;
    private String[] rollen;

    //Properties -> private Instalsvariablen mit Getter Setter
    @NotEmpty
    @Size(min=2, max=60, message="Name muss min 2 und max 60 Zeichen lang sein.")
    private String name;
    @NotEmpty
    @Email(message = "Muss eine richtige Email sein.")
    private String email;
    private String vegetarizitaet;
    private String rolle;
    @Losung(value = "42", value2 = "Zweiundvierzig", message="Die Losung muss 42 oder Zweiundvierzig enthalten.")
    private String losung;
    @Losung(value = "42", value2 = "Zweiundvierzig", message="Die Losung muss 42 oder Zweiundvierzig enthalten.")
    private String losungWiederh;
    
    public BenutzerFormular() {
        this.ernaerungsAuswahl = new String[]{"vegan", "vegetarisch", "alles"};
        this.rollen = new String[] {"ADMIN", "KUNDE", "GESPERRT"};
        this.name = "";
        this.email = "";
        this.vegetarizitaet = "0";
        this.rolle = "";
        this.losung = "";
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public void setErnaerungsAuswahl(String[] ernaerungsAuswahl) {
        this.ernaerungsAuswahl = ernaerungsAuswahl;
    }
    public void setRollen(String[] rollen) {
        this.rollen = rollen;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getLosungWiederh() {
        return losungWiederh;
    }
    public void setLosungWiederh(String losungWiederh) {
        this.losungWiederh = losungWiederh;
    }
    

    
    
}
