/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ie.philb.orderingws.service;

import ie.philb.orderingws.model.Party;
import java.util.List;

/**
 *
 * @author philb
 */
public interface IPartyService {

    public List<Party> getParties() throws ServiceException;

    public Party getParty(Long id) throws ServiceException;
}
