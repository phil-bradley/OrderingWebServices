package ie.philb.orderingws.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Country implements Serializable {

    private Long id;
    private String isoCode;
    private String name;
    private String iso3;

    public Country() {
    }

    public Country(Long id, String isoCode, String name, String iso3) {
        this.id = id;
        this.isoCode = isoCode;
        this.name = name;
        this.iso3 = iso3;
    }

}
