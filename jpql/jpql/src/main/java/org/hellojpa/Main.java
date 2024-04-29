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
            member.setMemberType(MemberType.USER);
            em.persist(member);

            em.flush();
            em.clear();

            String query = "select m.username, 'HELLO', TRUE from Member m where m.memberType = org.hellojpa.MemberType.USER";
            List<Object[]> result = em.createQuery(query).getResultList();
            System.out.println("result.size() = " + result.size());
            for(Object[] objects : result) {
                System.out.println("objects[0] = " + objects[0]);
                System.out.println("objects[1] = " + objects[1]);
                System.out.println("objects[2] = " + objects[2]);
            }

            String query2 = "select m.username, 'HELLO', TRUE from Member m where m.memberType = :userType";
            List<Object[]> result2 = em.createQuery(query2).setParameter("userType", MemberType.USER).getResultList();
            System.out.println("result2.size() = " + result2.size());
            for(Object[] objects : result2) {
                System.out.println("objects[0] = " + objects[0]);
                System.out.println("objects[1] = " + objects[1]);
                System.out.println("objects[2] = " + objects[2]);
            }

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