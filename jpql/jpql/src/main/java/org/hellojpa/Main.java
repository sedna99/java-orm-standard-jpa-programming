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
            member.setUsername("관리자");
            member.setAge(10);
            member.setTeam(team);
            member.setMemberType(MemberType.USER);
            em.persist(member);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            member2.setAge(10);
            member2.setTeam(team);
            member2.setMemberType(MemberType.USER);
            em.persist(member2);

            em.flush();
            em.clear();

            String query = "select concat('a', 'b') from Member m";
            List<String> result = em.createQuery(query, String.class).getResultList();
            for (String s : result) {
                System.out.println("s = " + s);
            }

            String query2 = "select substring(m.username, 2, 3) from Member m";
            List<String> result2 = em.createQuery(query2, String.class).getResultList();
            for (String s : result2) {
                System.out.println("s = " + s);
            }

            String query3 = "select trim('  abc  ') from Member m";
            List<String> result3 = em.createQuery(query3, String.class).getResultList();
            for (String s : result3) {
                System.out.println("s = " + s);
            }

            String query4 = "select lower('ABC') from Member m";
            List<String> result4 = em.createQuery(query4, String.class).getResultList();
            for (String s : result4) {
                System.out.println("s = " + s);
            }

            String query5 = "select locate('de', 'abcdefg') from Member m";
            List<Integer> result5 = em.createQuery(query5, Integer.class).getResultList();
            for (Integer s : result5) {
                System.out.println("s = " + s);
            }

            String query6 = "select ABS(-1) from Member m";
            List<Integer> result6 = em.createQuery(query6, Integer.class).getResultList();
            for (Integer s : result6) {
                System.out.println("s = " + s);
            }

            String query7 = "select length('abc') from Member m";
            List<Integer> result7 = em.createQuery(query7, Integer.class).getResultList();
            for (Integer s : result7) {
                System.out.println("s = " + s);
            }

            String query8 = "select size(t.members) from Team t";
            List<Integer> result8 = em.createQuery(query8, Integer.class).getResultList();
            for (Integer s : result8) {
                System.out.println("s = " + s);
            }

//            index()는 값 컬렉션의 인덱스를 조회할 때 사용
//            String query9 = "select index(t.members) from Team t";
//            List<Integer> result9 = em.createQuery(query9, Integer.class).getResultList();
//            for (Integer s : result9) {
//                System.out.println("s = " + s);
//            }

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