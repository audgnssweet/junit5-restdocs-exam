package study.h2.domain.member.dto;

import javax.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class MemberUpdateRequest {

    @NotEmpty
    private String name;

    MemberUpdateRequest(String name) {
        this.name = name;
    }

}
