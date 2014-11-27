package ie.philb.orderingws.model;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
import lombok.Data;

@Data
public class Party implements Serializable {

    private Long id;
    private String name;
    private String telephone;
    private String email;
    private String website;
    private String taxcode;
    private final Set<Address> address = new HashSet<>();

}
