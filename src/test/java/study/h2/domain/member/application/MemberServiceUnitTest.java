package study.h2.domain.member.application;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import study.h2.domain.member.dao.MemberRepository;

//Service에서 필요한 녀석들만 띄우면 된다.

/**
 * MockitoExtension = Mockito IOC Container을 띄운다.
 */

@ExtendWith(MockitoExtension.class)
class MemberServiceUnitTest {

    @InjectMocks    //등록된 Mock을 주입
    private MemberService memberService;

    @Mock   // Mockito Container에 Mock으로 등록
    private MemberRepository memberRepository;

}