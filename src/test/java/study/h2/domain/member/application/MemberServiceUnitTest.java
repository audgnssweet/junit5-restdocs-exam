package study.h2.domain.member.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Import;
import study.h2.common.ApplicationTestCommon;
import study.h2.common.config.MemberTestConfig;
import study.h2.domain.member.dao.MemberRepository;
import study.h2.domain.member.dto.MemberJoinRequest;
import study.h2.domain.member.dto.MemberJoinRequestBuilder;
import study.h2.domain.member.entity.Member;

//Service에서 필요한 녀석들만 띄우면 된다.

/**
 * MockitoExtension = Mockito IOC Container을 띄운다.
 */

@Import(MemberTestConfig.class)
@DisplayName("MemberServiceTest")
class MemberServiceUnitTest extends ApplicationTestCommon {

    @InjectMocks    //등록된 Mock을 주입
    private MemberService memberService;

    @Mock   // Mockito Container에 Mock으로 등록
    private MemberRepository memberRepository;

    @Test
    @DisplayName("addMember : succeed")
    void addMemberTest() {
        //given
        final MemberJoinRequest dto = MemberJoinRequestBuilder.build("member0");
        Member member0 = Member.builder().name("member0").build();

        when(memberRepository.save(any(Member.class))).thenReturn(member0);
        //when
        final Member added = memberService.addMember(dto);

        //then
        assertThat(added).isNotNull();
        assertThat(added.getName()).isEqualTo(member0.getName());
    }

}