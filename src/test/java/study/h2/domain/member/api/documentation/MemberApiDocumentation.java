package study.h2.domain.member.api.documentation;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

import org.springframework.restdocs.mockmvc.RestDocumentationResultHandler;
import org.springframework.restdocs.payload.JsonFieldType;

public class MemberApiDocumentation {

    public static RestDocumentationResultHandler joinMember() {
        return document("join-member",
            requestFields(
                fieldWithPath("name").type(JsonFieldType.STRING).description("회원 이름")
            ),
            responseFields(
                fieldWithPath("id").type(JsonFieldType.NUMBER).description("회원 PK"),
                fieldWithPath("name").type(JsonFieldType.STRING).description("회원 이름"),
                fieldWithPath("createAt").type(JsonFieldType.STRING).description("회원 생성일자"),
                fieldWithPath("updateAt").type(JsonFieldType.STRING).description("회원 정보 업데이트일자")
            )
        );
    }

    public static RestDocumentationResultHandler findAll() {
        return document("get-members",
            responseFields(
                fieldWithPath("memberList[]").type(JsonFieldType.ARRAY).description("회원 상세정보 리스트"),
                fieldWithPath("memberList[].id").type(JsonFieldType.NUMBER).description("회원 번호"),
                fieldWithPath("memberList[].name").type(JsonFieldType.STRING).description("회원 이름"),
                fieldWithPath("memberList[].createAt").type(JsonFieldType.STRING)
                    .description("회원 생성일자"),
                fieldWithPath("memberList[].updateAt").type(JsonFieldType.STRING)
                    .description("회원 정보 업데이트일자")
            )
        );
    }

    public static RestDocumentationResultHandler findById() {
        return document("get-member-detail",
            pathParameters(
                parameterWithName("memberId").description("회원의 PK")
            ),
            responseFields(
                fieldWithPath("id").type(JsonFieldType.NUMBER).description("회원 PK"),
                fieldWithPath("name").type(JsonFieldType.STRING).description("회원 이름"),
                fieldWithPath("createAt").type(JsonFieldType.STRING).description("회원 생성일자"),
                fieldWithPath("updateAt").type(JsonFieldType.STRING).description("회원 정보 업데이트일자")
            )
        );
    }

    public static RestDocumentationResultHandler findByIdError() {
        return document("get-member-error",
            pathParameters(
                parameterWithName("memberId").description("회원의 PK")
            ),
            responseFields(
                fieldWithPath("status").type(JsonFieldType.NUMBER).description("에러 상태코드"),
                fieldWithPath("message").type(JsonFieldType.STRING).description("에러 메시지")
            )
        );
    }
    
}
