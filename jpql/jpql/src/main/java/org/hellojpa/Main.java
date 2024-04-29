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

            em.flush();
            em.clear();

            List resultList = em.createQuery("select distinct m.username, m.age from Member m")
                    .getResultList();

            List<Object[]> resultList2 = em.createQuery("select distinct m.username, m.age from Member m")
                    .getResultList();

            List<MemberDTO> resultList3 = em.createQuery("select new org.hellojpa.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
                    .getResultList();

            for(Object o : resultList) {
                Object[] result = (Object[]) o;
                System.out.println("username = " + result[0]);
                System.out.println("age = " + result[1]);
            }

            for(Object[] result : resultList2) {
                System.out.println("username = " + result[0]);
                System.out.println("age = " + result[1]);
            }

            for(MemberDTO memberDTO : resultList3) {
                System.out.println("memberDTO = " + memberDTO);
                System.out.println("username = " + memberDTO.getUsername());
                System.out.println("age = " + memberDTO.getAge());
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