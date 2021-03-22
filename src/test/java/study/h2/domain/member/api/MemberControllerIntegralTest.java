package study.h2.domain.member.api;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import study.h2.common.IntegralTestCommon;
import study.h2.common.config.MemberTestConfig;
import study.h2.domain.member.dto.MemberJoinRequest;
import study.h2.domain.member.dto.MemberJoinRequestBuilder;
import study.h2.domain.member.entity.MemberSetup;

@Import(MemberTestConfig.class)
@DisplayName("Springboot Member Test")
public class MemberControllerIntegralTest extends IntegralTestCommon {

    @Autowired
    private MemberSetup memberSetup;

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
            .andDo(document("join-member",
                requestFields(
                    fieldWithPath("name").type(JsonFieldType.STRING).description("회원 이름")
                ),
                responseFields(
                    fieldWithPath("id").type(JsonFieldType.NUMBER).description("회원 PK"),
                    fieldWithPath("name").type(JsonFieldType.STRING).description("회원 이름"),
                    fieldWithPath("createAt").type(JsonFieldType.STRING).description("회원 생성일자"),
                    fieldWithPath("updateAt").type(JsonFieldType.STRING).description("회원 정보 업데이트일자")
                )
            ));
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

}