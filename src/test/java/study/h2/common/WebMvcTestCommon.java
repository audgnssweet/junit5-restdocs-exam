package study.h2.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

// Controller & Filter & ControllerAdvice 등 띄움
// @Extendwith(SpringExtension.class) - 스프링 환경에서 테스트. 매우 중요한 어노테이션.

@Disabled
@WebMvcTest
public abstract class WebMvcTestCommon {

    @Autowired  //근거. @WebMvcTestCommon 안에 @AutoConfigureMockMvc가 있기 때문
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

}
