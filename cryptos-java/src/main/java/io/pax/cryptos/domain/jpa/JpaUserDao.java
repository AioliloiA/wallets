package io.pax.cryptos.domain.jpa;


import io.pax.cryptos.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by AELION on 13/02/2018.
 */
public class JpaUserDao {

    JpaConnector connector = new JpaConnector();

   public JpaUser createUser (String name){

       EntityManager em = connector.createEntityManager();
       em.getTransaction().begin();

    JpaUser user = new JpaUser();
    user.setName(name);


    JpaWallet defaultWallet = new JpaWallet();
    defaultWallet.setName(name+"'s wallet");

    em.persist(defaultWallet);
    user.getWallets().add(defaultWallet);
    em.persist(user);

    em.getTransaction().commit();

    System.out.println("User id : "+user.getId());

       return user;
   }

    public JpaUser find (int id){
       EntityManager em = connector.createEntityManager();
       em.getTransaction().begin();

       JpaUser user = em.find(JpaUser.class, id);
       em.getTransaction().commit();
       em.close();
       return user;
    }


    public JpaUser findByName (String name){
        EntityManager em = connector.createEntityManager();
        em.getTransaction().begin();
        // JPQL
        TypedQuery<JpaUser> query = em.createQuery("SELECT u FROM JpaUser u WHERE u.name = :name", JpaUser.class);

        query.setParameter("name",name);

        List<JpaUser> users = query.getResultList();
        em.getTransaction().commit();
        em.close();

        if (users.size()>0) {
            System.out.println(users.get(0));
            return users.get(0);

        } else {
            return null;
            }

    }

    public void deletByName (String name){
        EntityManager em = connector.createEntityManager();
        em.getTransaction().begin();
        // JPQL
        TypedQuery<JpaUser> query = em.createQuery("SELECT u FROM JpaUser u WHERE u.name = :name", JpaUser.class);

        query.setParameter("name",name);

        List<JpaUser> users = query.getResultList();

        for (User u: users){
            em.remove(u);

            System.out.println(""+u.getName()+" deleted");
        }

        em.getTransaction().commit();
        em.close();



    }



    public static void main(String[] args) {
        JpaUserDao dao = new JpaUserDao();

        dao.createUser("Salut");

        //System.out.println(dao.find(2));
        System.out.println(dao.findByName("Arthur"));
        //dao.deletByName("Arthur");
        dao.connector.close();
    }
}
