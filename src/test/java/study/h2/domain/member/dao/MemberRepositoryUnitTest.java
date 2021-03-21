package study.h2.domain.member.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

// DB 관련 Bean들만 메모리에 뜬다.

/**
 * AutoConfigureTestDatabase - 테스트시 어떤 DB를 사용할 것인지
 * Replace.ANY - 가짜 DB, Replace.NONE - 진짜 DB
 * DataJpaTest - DB 관련 IoC 다 띄움
 */

@Transactional
@AutoConfigureTestDatabase(replace = Replace.ANY)
@DataJpaTest
class MemberRepositoryUnitTest {

    @Autowired
    private MemberRepository memberRepository;

}