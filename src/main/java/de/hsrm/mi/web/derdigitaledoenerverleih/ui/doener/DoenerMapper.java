package de.hsrm.mi.web.derdigitaledoenerverleih.ui.doener;

import de.hsrm.mi.web.derdigitaledoenerverleih.entities.doener.Doener;

public class DoenerMapper {
    
    public static Doener formularToDoener(DoenerFormular formular){
        Doener doener = new Doener();
        doener.setId(formular.getId());
        doener.setVersion(formular.getVersion());
        doener.setBezeichnung(formular.getBezeichnung());
        doener.setPreis(formular.getPreis());
        doener.setVegetarizitaet(formular.getVegetarizitaet());
        doener.setBestand(formular.getBestand());
        return doener;
    }

    public static DoenerFormular doenerToFormular(Doener doener){
        DoenerFormular formular = new DoenerFormular();
        formular.setId(doener.getId());
        formular.setVersion(doener.getVersion());
        formular.setBezeichnung(doener.getBezeichnung());
        formular.setPreis(doener.getPreis());
        formular.setVegetarizitaet(doener.getVegetarizitaet());
        formular.setBestand(doener.getBestand());
        return formular;
    }
}
