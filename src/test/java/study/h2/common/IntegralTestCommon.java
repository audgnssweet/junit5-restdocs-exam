package study.h2.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * SpringbootTest = 통합 테스트 어노테이션
 * webEnvironment = Tomcat을 어떤 환경으로 설정할 것인지
 * AutoConfigureMockMvc = MockMvc를 IOC에 등록해줌.
 * Transactional = 각각의 Test가 끝날 때마다 DB를 rollback해줌
 */

@Disabled
@Transactional
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@Import(RestDocsConfiguration.class)
@AutoConfigureRestDocs(uriScheme = "http", uriHost = "localhost", uriPort = 8080)
public abstract class IntegralTestCommon {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired  // JPA hibernate가 구현하는 것.
    protected EntityManager entityManager;

}
