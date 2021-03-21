package study.h2.domain.member.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.h2.domain.member.entity.Member;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class MembersResponse {

    List<Member> memberList;

    public MembersResponse(List<Member> memberList) {
        this.memberList = memberList;
    }

}
