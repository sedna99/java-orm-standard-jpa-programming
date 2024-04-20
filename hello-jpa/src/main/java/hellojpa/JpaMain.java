package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Movie movie = new Movie();
            movie.setDirector("AAAA");
            movie.setActor("BBBB");
            movie.setName("바람과 함꼐 사라지다");
            movie.setPrice(10000);

            em.persist(movie);

            em.flush();
            em.clear();

            Movie foundMovie = em.find(Movie.class, movie.getId());
            System.out.println(movie.getId());
            System.out.println("===========");
            System.out.println("foundMovie = " + foundMovie);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
