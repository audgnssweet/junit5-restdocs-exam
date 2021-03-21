package study.h2.domain.member.dto;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.h2.domain.member.entity.Member;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class MemberResponse {

    private Long id;

    private String name;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    public MemberResponse(Member member) {
        this.id = member.getId();
        this.name = member.getName();
        this.createAt = member.getCreateAt();
        this.updateAt = member.getUpdateAt();
    }

}
