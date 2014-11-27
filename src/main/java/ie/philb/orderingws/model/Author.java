/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Author implements Serializable {

    private String firstname = "";
    private String surname = "";
    private String email = "";

    public Author() {
    }

    public Author(String firstname, String surname, String email) {
        this.firstname = firstname;
        this.surname = surname;
        this.email = email;
    }

}
