package org.jpabook;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.jpabook.jpashop.Book;
import org.jpabook.jpashop.Member;
import org.jpabook.jpashop.Order;
import org.jpabook.jpashop.OrderItem;

import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        Member member1 = new Member();
        member1.setName("kim");
        em.persist(member1);

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            List<Member> members = em.createQuery("select m from Member m where m.name" +
                            " like '%kim'", Member.class)
                    .getResultList();
            for (Member member : members) {
                System.out.println("member = " + member.getName());
            }

            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);
            Root<Member> m = query.from(Member.class);

            String username = "kim";
            if (username != null) {
                query = query.where(cb.equal(m.get("name"), "kim"));
            }

            query.select(m).where(cb.equal(m.get("name"), "kim"));
            List<Member> members2 = em.createQuery(query).getResultList();
            for (Member member : members2) {
                System.out.println("member = " + member.getName());
            }

            List<Member> memberList = em.createNativeQuery("select * from member", Member.class).getResultList();
            for (Member member : memberList) {
                System.out.println("member = " + member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
