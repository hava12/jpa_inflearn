package jpabook.jpashop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;

public class JpaMain {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();

		try {
			Order order = em.find(Order.class, 1L);
			Long memberId = order.getMemberId();

			// 객체지향스럽지 않다.
			// 데이터 중심 설계
			// 데이터 중심 설계의 문제점
			// 객체 설계를 테이블 설계에 맞춘 방식..
			// 테이블의 외래키를 객체에 그대로 가져와 객체 그래프 탐색이 불가능
			// 참조가 없으므로 UML도 잘못됨
			Member member = em.find(Member.class, memberId);

			// 이게 객체지향스럽지 않을까
			Member findMember = order.getMember();

		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}


}
