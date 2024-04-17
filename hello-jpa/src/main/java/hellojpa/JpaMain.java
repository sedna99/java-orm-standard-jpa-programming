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
            Member member = new Member();
//            member.setId("ID_A");
            member.setUsername("B");
            member.setRoleType(RoleType.GUEST);

            System.out.println("============");

            em.persist(member);
            System.out.println("member.id :" + member.getId().toString());
            System.out.println("============");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
