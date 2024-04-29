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
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("teamA");
            member.setAge(10);
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            String query = "select m from Member m left join m.team t";
            List<Member> result = em.createQuery(query, Member.class).getResultList();

            System.out.println("results.size() = " + result.size());

            String query2 = "select m from Member m, Team t where m.username = t.name";
            List<Member> result2 = em.createQuery(query2, Member.class).getResultList();

            System.out.println("results2.size() = " + result2.size());

            String query3 = "select m from Member m left join m.team t on t.name = 'teamA'";
            List<Member> result3 = em.createQuery(query3, Member.class).getResultList();

            System.out.println("results3.size() = " + result3.size());

            String query4 = "select m from Member m left join Team t on m.username = t.name";
            List<Member> result4 = em.createQuery(query4, Member.class).getResultList();

            System.out.println("results4.size() = " + result4.size());


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