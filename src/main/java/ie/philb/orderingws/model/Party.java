package ie.philb.orderingws.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class Party implements Serializable {

    private Long id = 0L;
    private String name = "";
    private String telephone = "";
    private String email = "";
    private String website = "";
    private String taxcodev;
    private final List<Address> addresses = new ArrayList<>();

    public Party() {
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses.clear();

        if (addresses != null) {
            this.addresses.addAll(addresses);
        }
    }

    public List<Address> getAddresses() {
        return addresses;
    }
}
