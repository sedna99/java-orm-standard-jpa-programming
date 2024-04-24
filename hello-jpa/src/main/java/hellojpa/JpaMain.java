package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Team team2 = new Team();
            team2.setName("teamB");
            em.persist(team2);

            Member member1 = new Member();
            member1.setCreatedBy("kim");
            member1.setUsername("member1");
            member1.setCreatedDate(LocalDateTime.now());
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setCreatedBy("kim");
            member2.setUsername("member2");
            member2.setCreatedDate(LocalDateTime.now());
            member2.setTeam(team2);
            em.persist(member2);

            em.flush();
            em.clear();

            Member foundMember = em.find(Member.class, member1.getId());
            System.out.println("findMember1 = " + foundMember.getClass());
            Team foundTeam = foundMember.getTeam();
            System.out.println("foundTeam = " + foundTeam.getClass());
            System.out.println("teamName = " + foundTeam.getName());

            em.flush();
            em.clear();

            List<Member> members = em.createQuery("select m from Member m join fetch m.team", Member.class)
                    .getResultList();
            for(Member m : members) {
                System.out.println("member = " + m.getUsername() + ", " + m.getTeam().getName());
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
