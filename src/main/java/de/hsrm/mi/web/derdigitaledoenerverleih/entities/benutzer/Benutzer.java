package de.hsrm.mi.web.derdigitaledoenerverleih.entities.benutzer;

import de.hsrm.mi.web.derdigitaledoenerverleih.ui.validators.Losung;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class Benutzer {
    
    @Id
    //@Column(unique=true, nullable=false)//von Chatti
    private String loginName;

    @Version
    long version;

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
    
    public String getLoginName() {
        return loginName;
    }
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
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
    
    @Override
    public String toString() {
        return "Benutzer [loginName=" + loginName + ", name=" + name + ", email=" + email + ", vegetarizitaet="
                + vegetarizitaet + ", rolle=" + rolle + ", losung=" + losung + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Benutzer)) return false;
        Benutzer benutzer = (Benutzer) o;
        return loginName.equals(benutzer.loginName);
    }
    public long getVersion() {
        return version;
    }
    public void setVersion(long version) {
        this.version = version;
    }

}
