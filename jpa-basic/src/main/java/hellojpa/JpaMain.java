package hellojpa;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	public static void main(String[] args) {
		// Application 로딩 시점에 딱 하나만 만들자
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		try {
			// CREATE
			// Member member = new Member();
			// member.setId(1l);
			// member.setName("HelloA");

			// SELECT
			// Member member = em.find(Member.class, 1l);// 2번째 인자로 PK
			// System.out.println("findMember.id " + member.getId());
			// System.out.println("findMember.name " + member.getName());

			// em.persist(member);

			// 삭제
			// em.remove(member);

			// 수정
			// member.setName("helloJPA");

			// SQL을 추상화한 JPQL
			// JPQL 객체를 대상으로 하는 객체지향 쿼리
			List<Member> result =
				em.createQuery("select m from Member as m ", Member.class)
					.setFirstResult(1) // Pagination
					.setMaxResults(10) // Pagination
					.getResultList();

			for (Member member : result ) {
				System.out.println("member.getName() = " + member.getName());
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
