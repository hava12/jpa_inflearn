package hellojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// 영속, 비영속, 준영속, 삭제
public class JpaMain3 {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {

			Team team = new Team();
			team.setName("TeamA");
			em.persist(team);

			Member member = new Member();
			member.setUsername("member1");
			member.setTeam(team);

			em.persist(member);

			Member findMember = em.find(Member.class, member.getId());

			Team findTeam = findMember.getTeam();
			System.out.println("findTeam = " + findTeam);

			// Team newTeam = em.find(Team.class, 100L);
			// findMember.setTeam(newTeam);

			List<Member> members = findMember.getTeam().getMembers();

			for (Member m : members) {
				System.out.println("m = " + m.getUsername());
			}

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}
}
