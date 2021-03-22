package study.h2.common.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import study.h2.domain.member.entity.MemberSetup;

@TestConfiguration
public class MemberTestConfig {

    @Bean
    public MemberSetup memberSetup() {
        return new MemberSetup();
    }

}
