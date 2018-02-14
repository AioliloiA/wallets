package io.pax.cryptos.business;

import io.pax.cryptos.domain.Wallet;
import io.pax.cryptos.domain.jpa.JpaWallet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**Stateless: java ee managed ejbCreated by AELION on 06/02/2018.
 */
@Stateless
public class WalletBusiness {

    // Entity manager is given by WF and is a managed object
@PersistenceContext
    EntityManager em;

    public Wallet findWallet(int id){
        // transaction open in your back
        return  em.find(JpaWallet.class, id);
    }

}
