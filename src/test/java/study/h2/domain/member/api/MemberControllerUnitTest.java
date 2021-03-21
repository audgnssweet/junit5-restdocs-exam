package study.h2.domain.member.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import study.h2.common.WebMvcTestCommon;
import study.h2.domain.member.application.MemberService;
import study.h2.domain.member.dto.MemberJoinRequest;
import study.h2.domain.member.dto.MemberJoinRequestBuilder;
import study.h2.domain.member.dto.MemberUpdateRequest;
import study.h2.domain.member.dto.MemberUpdateRequestBuilder;
import study.h2.domain.member.entity.MemberSetup;

@DisplayName("Member Test")
class MemberControllerUnitTest extends WebMvcTestCommon {

    @MockBean   //Mock객체를 Spring IOC Container에 Bean으로 등록해줌.
    private MemberService memberService;

    private MemberSetup memberSetup;

    @BeforeEach
    void setUp() {
        memberSetup = new MemberSetup();
    }

    // BDDMockito pattern - given, when, then
    // 각 테스트는 독립적이어야 함.
    @Test
    @DisplayName("save : succeed")
    void postMembersTest() throws Exception {

        //given
        final MemberJoinRequest request = MemberJoinRequestBuilder.build("jeong");
        final String requestJson = this.objectMapper.writeValueAsString(request);

        when(memberService.addMember(any())).thenReturn(memberSetup.build("jeong"));

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
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("find all : succeed")
    void getMembersTest() throws Exception {
        //given
        when(memberService.findAll()).thenReturn(memberSetup.buildListAuto(3));

        //when
        final ResultActions resultActions = this.mockMvc.perform(get("/members")
            .accept(MediaType.APPLICATION_JSON));

        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.memberList", Matchers.hasSize(3)))
            .andExpect(jsonPath("$.memberList.[0].name").value("member0"))
            .andExpect(jsonPath("$.memberList.[1].name").value("member1"))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("findById : succeed")
    void getByIdTest() throws Exception {
        //given
        when(memberService.findById(any())).thenReturn(memberSetup.buildOneAuto());
        Long id = 1L;
        //when
        final ResultActions resultActions = this.mockMvc.perform(get("/members/{id}", id)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.name").value("member0"))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("updateById : succeed")
    void updateByIdTest() throws Exception {
        //given
        final MemberUpdateRequest dto = MemberUpdateRequestBuilder.build("updated");
        final String jsonDto = objectMapper.writeValueAsString(dto);

        when(memberService.updateById(anyLong(), any(MemberUpdateRequest.class)))
            .thenReturn(memberSetup.build("updated"));
        //when
        final ResultActions resultActions = this.mockMvc.perform(put("/members/{id}", 1L)
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonDto)
            .accept(MediaType.APPLICATION_JSON));
        //then
        resultActions.andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value("updated"))
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("deleteById : succeed")
    void deleteByIdTest() {
        //given
        when(memberService.deleteById(anyLong())).thenReturn(null);

    }

}