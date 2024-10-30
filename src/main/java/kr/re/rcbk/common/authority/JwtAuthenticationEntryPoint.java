package kr.re.rcbk.common.authority;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.re.rcbk.common.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @name : JwtAuthenticationEntryPoint
 * 401 에러
 */
@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

    @Autowired
    ObjectMapper objectMapper;

    /**
     * 유효한 자격증명을 제공하지 않고 접근시 401
     * @param request that resulted in an <code>AuthenticationException</code>
     * @param response so that the user agent can begin authentication
     * @param authException that caused the invocation
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {

        log.error("401(Unauthorized) error");

        BaseResponse<?> baseResponse = BaseResponse.error(HttpStatus.UNAUTHORIZED, "401(Unauthorized) error");

        String responseContent = objectMapper.writeValueAsString(baseResponse);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(responseContent);

    }
}
