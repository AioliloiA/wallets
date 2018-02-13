package io.pax.cryptos.domain.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by AELION on 13/02/2018.
 */
public class JpaConnector {

    EntityManagerFactory factory;


    void connect(){
        if (this.factory == null){
            this.factory = Persistence.createEntityManagerFactory("cryptos");
        }
    }

    public EntityManager createEntityManager() {

        this.connect();
        return factory.createEntityManager();
    }

    public void close(){
        this.factory.close();
    }

    public static void main(String[] args) {
        JpaConnector connector = new JpaConnector();
        EntityManager em = connector.createEntityManager();


        JpaUser jean = new JpaUser();
        jean.setName("Jean");

        JpaUser jack = new JpaUser();
        jack.setName("Jack");

        JpaUser jackie = new JpaUser();
        jackie.setName("Jackie");

        JpaUser jules = new JpaUser();
        jules.setName("Jules");

        em.getTransaction().begin();

        em.persist(jack);
        em.persist(jean);
        em.persist(jackie);
        em.persist(jules);

        em.getTransaction().commit();

        em.close();
    }
}
