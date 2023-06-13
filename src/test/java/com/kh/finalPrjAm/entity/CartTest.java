package com.kh.finalPrjAm.entity;
import com.kh.finalPrjAm.repository.CartRepository;
import com.kh.finalPrjAm.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest // 스프링 컨텍스트를 로드하여 테스트 환경 설정
@Transactional  // 데이터베이스의 논리적인 작업 단위, 모두 성공하는 경우가 아니면 롤백(테스트에서는 ✨성공 여부 상관없이 자동 롤백)
@Slf4j          // 로깅 데이터를 처리하기 위해 사용
@TestPropertySource(locations = "classpath:application-test.properties")
class CartTest {
    @Autowired  // 스프링 컨테이너에서 해당 빈에 해당하는 의존성 주입 받음(방법 3가지 : 생성자, setter, 필드)
    CartRepository cartRepository;
    @Autowired
    MemberRepository memberRepository;
    @PersistenceContext // EntityManager를 사용하기 위해서..
    EntityManager em;

    // ✨회원 엔티티 생성
    public Member createMemberInfo() {
        // 추후에는 클라이언트에서 받은 데이터를 Controller와 서비스로직을 거쳐서 ..
        Member member = new Member();
        member.setUserId("test2024");
        member.setPassword("password1234");
        member.setName("홍길동");
        member.setEmail("test2024@naver.com");
        member.setJoinTime(LocalDateTime.now());
        return member;
    }
    @Test
    @DisplayName("장바구니 회원 매핑 조회 테스트")
    public void findCartAndMemberTest() {
        // 1번, 회원가입 완료 ---테스트를 위해서 임의로
        Member member = createMemberInfo();
        memberRepository.save(member);

        // 2번, 가입한 회원의 카트 생성
        Cart cart = new Cart();
        cart.setMember(member);
        cart.setCartName("테스트용 장바구니");
        cartRepository.save(cart);

        em.flush(); // 실제 DB에 반영!!
        em.clear();

        // orElseThrow(EntityNotFoundException::new) : 테스트 할 때'만' 쓰는 문법
        Cart saveCart = cartRepository.findById(cart.getId()).orElseThrow(EntityNotFoundException::new);
        System.out.println("🔴회원 userID : " + member.getUserId());
        System.out.println("🔴장바구니 Entity 회원의 userID : " + saveCart.getMember().getUserId());
        // 테스트 환경에서 성공 여부 확인하기 위해 사용
        assertEquals(saveCart.getMember().getUserId(), member.getUserId());
        // ☘️테스트에서는 성공 여부 상관없이 롤백 되기 때문에 DB에서는 확인 불가하니 터미널에서 확인할 것!!!!
    }
}