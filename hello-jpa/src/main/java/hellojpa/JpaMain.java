package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member1 = new Member();
            member1.setCreatedBy("kim");
            member1.setUsername("member1");
            member1.setCreatedDate(LocalDateTime.now());
            em.persist(member1);

            Member member2 = new Member();
            member2.setCreatedBy("kim");
            member2.setUsername("member2");
            member2.setCreatedDate(LocalDateTime.now());
            em.persist(member2);

            em.flush();
            em.clear();

//            Member m1 = em.find(Member.class, member1.getId());
//            Member m2 = em.getReference(Member.class, member2.getId());

            Member refMember = em.getReference((Member.class), member1.getId());
            System.out.println("refMember = " + refMember.getClass());
            Hibernate.initialize(refMember);
            System.out.println("refMember is loaded? = " + emf.getPersistenceUnitUtil().isLoaded(refMember));
            System.out.println("refMember = " + refMember.getClass());

//            Member findMember = em.find((Member.class), member1.getId());
//            System.out.println("findMember = " + findMember.getClass());
//
//            System.out.println("refMember == findMember : " + (refMember == findMember));
//            System.out.println("m1 = " + m1.getClass());
//
//            System.out.println("m1 == m2 : " + (m1.getClass() == m2.getClass()));
//
//            logic(m1, m2);

//            Member findMember = em.find(Member.class, member.getId());
//            Member findMember = em.getReference(Member.class, member1.getId());
//            System.out.println("before findMember = " + findMember.getClass());
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.username = " + findMember.getUsername());
//            System.out.println("findMember.username = " + findMember.getUsername());
//            System.out.println("after findMember = " + findMember.getClass());
//            printMember(member);
//
//            em.getReference();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }

    private static void printMember(Member member) {
        System.out.println("member = " + member.getUsername());
    }

    private static void printMemberAndTeam(Member member) {
        String username = member.getUsername();
        System.out.println("username = " + username);

        Team team = member.getTeam();
        System.out.println("team = " + team.getName());
    }

    public static void logic(Member m1, Member m2) {
        System.out.println("m1 == m2 : " + (m1.getClass() == m2.getClass()));
        System.out.println("m1 instance : " + (m1 instanceof Member));
        System.out.println("m2 instance : " + (m2 instanceof Member));
    }
}
