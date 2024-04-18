package org.jpabook;

import jakarta.persistence.*;
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

             Order order = new Order();
             order.addOrderItem(new OrderItem());
//             Order order = em.find(Order.class, 1L);


//            Member orderedMember = em.find(Member.class, memberId);
//

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
