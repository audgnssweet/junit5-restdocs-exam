package study.h2.domain.member.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import study.h2.common.DataJpaTestCommon;
import study.h2.common.config.MemberTestConfig;
import study.h2.domain.member.entity.Member;
import study.h2.domain.member.entity.MemberSetup;

// DB 관련 Bean들만 메모리에 뜬다.

/**
 * AutoConfigureTestDatabase - 테스트시 어떤 DB를 사용할 것인지 Replace.ANY - 가짜 DB, Replace.NONE - 진짜 DB
 * DataJpaTest - DB 관련 IoC 다 띄움
 * <p>
 * Jpa Repository에서 직접 제공하는 메서드는 테스트 X NativeQuery를 주로 테스트
 */

@Import(MemberTestConfig.class)
class MemberRepositoryUnitTest extends DataJpaTestCommon {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberSetup memberSetup;

    @BeforeEach
    void setUp() {
        this.entityManager
            .createNativeQuery("ALTER TABLE member ALTER COLUMN member_id RESTART WITH 1")
            .executeUpdate();
    }

    @Test
    void saveMemberTest() {
        //given
        final Member member = memberSetup.buildOneAuto();
        //when
        final Member saved = memberRepository.save(member);
        //then
        assertThat(saved).isNotNull();
        assertThat(saved.getName()).isEqualTo("member0");
        assertThat(saved.getId()).isEqualTo(1L);
    }

}