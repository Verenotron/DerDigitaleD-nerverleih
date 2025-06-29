package de.hsrm.mi.web.derdigitaledoenerverleih.api.doener;

import java.util.List;

import de.hsrm.mi.web.derdigitaledoenerverleih.entities.doener.Doener;
import de.hsrm.mi.web.derdigitaledoenerverleih.entities.zutat.Zutat;

public record DoenerDTO(
    long id,
    long version,
    String bezeichnung,
    int preis,
    int vegetarizitaet,
    int bestand,
    List<Zutat> zutaten,
    int verfuegbar
) {

    public static DoenerDTO fromEntity(Doener doener) {
        return new DoenerDTO(
            doener.getId(),
            doener.getVersion(),
            doener.getBezeichnung(),
            doener.getPreis(),
            doener.getVegetarizitaet(),
            doener.getBestand(),
            doener.getZutaten(),
            doener.getVerfuegbar()
        );
    }

    public static Doener toEntity(DoenerDTO dto) {
        Doener d = new Doener();
        d.setId(dto.id());
        d.setVersion(dto.version());
        d.setBezeichnung(dto.bezeichnung());
        d.setPreis(dto.preis());
        d.setVegetarizitaet(dto.vegetarizitaet());
        d.setBestand(dto.bestand());
        d.setZutaten(dto.zutaten());
        return d;
    }
    
}
