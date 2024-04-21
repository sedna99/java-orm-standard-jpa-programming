package org.jpabook;

import jakarta.persistence.*;
import org.jpabook.jpashop.Book;
import org.jpabook.jpashop.Member;
import org.jpabook.jpashop.Order;
import org.jpabook.jpashop.OrderItem;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

             Book book = new Book();
             book.setName("JPA");
             book.setAuthor("kim");

             em.persist(book);

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
