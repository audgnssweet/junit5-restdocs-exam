package study.h2.domain.member.dto;

public class MemberJoinRequestBuilder {

    public static MemberJoinRequest build(String name) {
        return new MemberJoinRequest(name);
    }

}
