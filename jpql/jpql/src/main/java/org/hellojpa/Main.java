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
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member1 = new Member();
            member1.setUsername("관리자1");
            member1.setMemberType(MemberType.ADMIN);
            member1.setTeam(team);

            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            member2.setMemberType(MemberType.ADMIN);
            member2.setTeam(team);

            em.persist(member2);

            em.flush();
            em.clear();

            String query = "select m.username from Team t join t.members m";
            em.createQuery(query, List.class)
                    .getResultList()
                    .forEach(System.out::println);

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