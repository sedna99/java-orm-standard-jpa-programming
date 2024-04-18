package hellojpa;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Team team = new Team();
            team.setName("TeamA");
//            team.getMembers().add(member);
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            team.addMember(member);
            em.persist(member);

//            team.getMembers().add(member);
//            em.flush();
//            em.clear();

            Team foundTeam = em.find(Team.class, team.getId());
            List<Member> foundMembers = foundTeam.getMembers();
            for (Member m : foundMembers) {
                System.out.println("m = " + m.getUsername());
            }

//            Member foundMember = em.find(Member.class, member.getId());
//            Team foundTeam = foundMember.getTeam();
//            List<Member> foundTeamMembers = foundTeam.getMembers();
//            for (Member m : foundTeamMembers) {
//                System.out.println("m = " + m.getUsername());
//            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
