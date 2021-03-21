package study.h2.domain.member.dto;


public class MemberUpdateRequestBuilder {

    public static MemberUpdateRequest build(String name) {
        return new MemberUpdateRequest(name);
    }
}
