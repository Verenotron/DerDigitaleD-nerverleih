package de.hsrm.mi.web.derdigitaledoenerverleih.api.zutat;

import de.hsrm.mi.web.derdigitaledoenerverleih.entities.zutat.Zutat;

public record ZutatDTO(
    String ean,
    long version,
    String name,
    int vegetarizitaet
) {
    public static ZutatDTO fromEntity(Zutat zutat) {
        return new ZutatDTO(
            zutat.getEan(),
            zutat.getVersion(),
            zutat.getName(),
            zutat.getVegetarizitaet()
        );
    }

    public static Zutat toEntity(ZutatDTO dto) {
        Zutat z = new Zutat();
        z.setEan(dto.ean());
        z.setVersion(dto.version());
        z.setName(dto.name());
        z.setVegetarizitaet(dto.vegetarizitaet());
        return z;
    }
}
