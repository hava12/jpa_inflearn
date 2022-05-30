package hellojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// 연관관계의 주인
public class JpaMain4 {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {

			// 양방향 매핑 시 가장 많이 하는 실수
			// 연관관계의 주인에 값을 입력하지 않음
			Team team = new Team();
			team.setName("TeamA");
			em.persist(team);

			Member member = new Member();
			member.setUsername("member1");
			member.changeTeam(team);
			em.persist(member);

			// Member를 기준으로 Team을 넣을지
			// Team을 기준으로 Member를 넣을 지 정해야함
			team.addMember(member);

			team.getMembers().add(member);

			// em.flush();
			// em.clear();
			// flush, clear를 하지 않는 경우에 문제가 생길 수 있다.
			// 항상 양쪽에 값을 설정하자

			Team findTeam = em.find(Team.class, team.getId()); // 1차 캐시
			List<Member> members = findTeam.getMembers();

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
