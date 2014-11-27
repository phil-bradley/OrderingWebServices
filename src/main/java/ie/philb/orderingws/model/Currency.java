/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class Currency implements Serializable {

    private String name;
    private String code;

    public Currency(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public Currency() {
    }

}
