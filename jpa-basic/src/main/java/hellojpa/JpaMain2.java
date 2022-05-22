package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// 영속, 비영속, 준영속, 삭제
public class JpaMain2 {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			// 비영속
			Member member = new Member();
			// member.setId(101l);
			// member.setName("HelloJPa");

			// 영속 - DB에 저장되는 것 아님. 영속성 컨텍스트에 저장되는 것.
			System.out.println(" === BEFORE === ");
			em.persist(member);
			System.out.println(" === AFTER === ");

			// 준영속 - 영속성 컨텍스트에서 제거
			// em.detach(member);

			// 삭제 - DB 삭제
			// em.remove(member);
			
			// 영속성 컨텍스트의 이점
			// - 1차 캐시
			// - 동일성 보장
			// - 트랜잭션을 지원하는 쓰기 지연
			// - 변경 감지
			// - 지연 로딩
			em.find(Member.class, 101l);

			// 플러시 (flush)
			// 영속성 컨텍스트의 변경 내용을 데이터베이스에 반영
			// 영속성 컨텍스트를 플러시하는 방법
			// em.flush() 직접 호출
			// JPQL 사용 시 자동 호출
			// commit 시 자동 호출

			// System.out.println("member.getId() = " + member.getId());
			// System.out.println("member.getName() = " + member.getName());

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}
}
