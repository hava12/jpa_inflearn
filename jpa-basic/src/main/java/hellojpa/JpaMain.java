package hellojpa;

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
			Member member = em.find(Member.class, 1l);// 2번째 인자로 PK
			// System.out.println("findMember.id " + member.getId());
			// System.out.println("findMember.name " + member.getName());

			// em.persist(member);

			// 삭제
			// em.remove(member);

			// 수정
			member.setName("helloJPA");

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}

		emf.close();

	}
}
