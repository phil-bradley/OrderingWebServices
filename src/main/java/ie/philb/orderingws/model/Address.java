package ie.philb.orderingws.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Address implements Serializable {

    private Long id;
    private String street1;
    private String street2;
    private String city;
    private String state;
    private String zipcode;
    private Country country;

}
