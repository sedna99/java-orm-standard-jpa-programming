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

            Team team2 = new Team();
            team2.setName("TeamB");

            em.persist(team);
            em.persist(team2);


            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setMemberType(MemberType.USER);
            member1.setTeam(team);

            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setMemberType(MemberType.USER);
            member2.setTeam(team);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setMemberType(MemberType.USER);
            member3.setTeam(team2);

            em.persist(member2);

            em.persist(member3);

            em.flush();
            em.clear();

            List<Member> resultList = em.createNamedQuery("Member.findByUsername", Member.class)
                    .setParameter("username", "회원1")
                    .getResultList();

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