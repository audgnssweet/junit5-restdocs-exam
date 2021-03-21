package study.h2.domain.member.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.h2.domain.member.entity.Member;

// 동일 패키지 = 테스트에서도 적용가능
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class MemberJoinRequest {

    @NotEmpty
    private String name;

    public Member toEntity() {
        return Member.builder()
            .name(name)
            .build();
    }

    //Test를 위한 PACKAGE단위
    MemberJoinRequest(String name) {
        this.name = name;
    }

}
