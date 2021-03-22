package study.h2.domain.member.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import study.h2.common.IntegralTestCommon;
import study.h2.common.config.MemberTestConfig;
import study.h2.domain.member.api.documentation.MemberApiDocumentation;
import study.h2.domain.member.dao.MemberRepository;
import study.h2.domain.member.dto.MemberJoinRequest;
import study.h2.domain.member.dto.MemberJoinRequestBuilder;
import study.h2.domain.member.entity.Member;
import study.h2.domain.member.entity.MemberSetup;

@Import(MemberTestConfig.class)
@DisplayName("Springboot Member Test")
public class MemberControllerIntegralTest extends IntegralTestCommon {

    @Autowired
    private MemberSetup memberSetup;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        //H2 DB인 경우
        this.entityManager
            .createNativeQuery("ALTER TABLE member ALTER COLUMN member_id RESTART WITH 1")
            .executeUpdate();
//        //MYSQL인 경우
//        this.entityManager
//            .createNativeQuery("ALTER TABLE member AUTO_INCREMENT=1").executeUpdate();
    }

    // BDDMockito pattern - given, when, then
    @Test
    @DisplayName("save : succeed")
    void testJoin() throws Exception {

        //given
        final MemberJoinRequest request = MemberJoinRequestBuilder.build("jeong");
        final String requestJson = this.objectMapper.writeValueAsString(request);

        //when
        final ResultActions resultActions = this.mockMvc.perform(post("/members")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson)
            .accept(MediaType.APPLICATION_JSON));//return하는 contentType

        //then
        //그 객체가 무엇인지는 신경 안 쓴다. 단지 JSON값으로 읽을 뿐.
        resultActions
            .andExpect(status().isCreated())
            //JsonPath 문법
            .andExpect(jsonPath("$.name").value("jeong"))
            .andExpect(jsonPath("$.id").isNotEmpty())
            .andExpect(jsonPath("$.createAt").isNotEmpty())
            .andExpect(jsonPath("$.updateAt").isNotEmpty())
            .andDo(MockMvcResultHandlers.print())
            .andDo(MemberApiDocumentation.joinMember());
    }

    @Test
    @DisplayName("save : fail")
    void testJoinFail() throws Exception {
        //given
        final MemberJoinRequest request = MemberJoinRequestBuilder.build(null);
        final String requestJson = this.objectMapper.writeValueAsString(request);
        //when
        final ResultActions resultActions = this.mockMvc.perform(post("/members")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestJson)
            .accept(MediaType.APPLICATION_JSON));//return하는 contentType
        //then
        resultActions
            .andExpect(status().isBadRequest())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("findAll : succeed")
    void testFindAll() throws Exception {
        //given
        final List<Member> memberList = memberSetup.buildListAuto(3);
        memberRepository.saveAll(memberList);
        //when
        final ResultActions resultActions = this.mockMvc.perform(get("/members")
            .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.memberList", Matchers.hasSize(3)))
            .andExpect(jsonPath("$.memberList.[0].id").value(1L))
            .andExpect(jsonPath("$.memberList.[0].name").value("member0"))
            .andExpect(jsonPath("$.memberList.[0].createAt").isNotEmpty())
            .andExpect(jsonPath("$.memberList.[0].updateAt").isNotEmpty())
            .andDo(MockMvcResultHandlers.print())
            .andDo(MemberApiDocumentation.findAll());
    }

    @Test
    @DisplayName("findById : succeed")
    void testFindById() throws Exception {
        //given
        final List<Member> memberList = memberSetup.buildListAuto(3);
        memberRepository.saveAll(memberList);
        Long id = 1L;
        //when
        final ResultActions resultActions = this.mockMvc
            .perform(RestDocumentationRequestBuilders.get("/members/{memberId}", id)
                .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1L))
            .andExpect(jsonPath("$.name").value("member0"))
            .andExpect(jsonPath("$.createAt").isNotEmpty())
            .andExpect(jsonPath("$.updateAt").isNotEmpty())
            .andDo(MockMvcResultHandlers.print())
            .andDo(MemberApiDocumentation.findById());
    }

    @Test
    @DisplayName("findById : fail")
    void findByIdTestFail() throws Exception {
        //given
        final List<Member> memberList = memberSetup.buildListAuto(3);
        memberRepository.saveAll(memberList);
        Long id = 4L;
        //when
        final ResultActions resultActions = this.mockMvc
            .perform(RestDocumentationRequestBuilders.get("/members/{memberId}", id)
                .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions.andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.status").value(400))
            .andExpect(jsonPath("$.message", Matchers.containsString("없습니다")))
            .andDo(MockMvcResultHandlers.print())
            .andDo(MemberApiDocumentation.findByIdError());
    }
}