package study.h2.domain.member.entity;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class MemberEntityTest {

    @Test
    void updateTest() {
        final Member member = Member.builder()
            .name("first")
            .build();

        member.update("updated");
        assertThat(member.getName()).isEqualTo("updated");
    }

}
