package person;


import legoset.model.LegoSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.Year;
import java.util.List;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Main {

    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-example");

    private static void addRandomPerson(int count) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            for (int i = 0; i < count; i++) {
                em.persist(Person.randomPerson());
            }


            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static List<Person> getPersonList() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT rp FROM Person rp ORDER BY rp.id", Person.class).getResultList();
        } finally {
            em.close();
        }
    }



    public static void main(String[] args) {

       addRandomPerson(100);


        //getPersonList().forEach(log::info);




    }


}
