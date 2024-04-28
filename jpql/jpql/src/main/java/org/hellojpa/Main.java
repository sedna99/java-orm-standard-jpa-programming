package org.hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);

            em.persist(member);

            TypedQuery<Member> query1 = em.createQuery("select m from Member m", Member.class);
            List<Member> resultList = query1.getResultList();

            TypedQuery<Member> query2 = em.createQuery("select m from Member m where m.id = :id", Member.class);
            query2.setParameter("id", member.getId());
            Member result = query2.getSingleResult();

            System.out.println("result = " + result.getUsername());

            TypedQuery<String> query3 = em.createQuery("select m.username from Member m", String.class);

            Query query4 = em.createQuery("select m.username, m.age from Member m");


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}