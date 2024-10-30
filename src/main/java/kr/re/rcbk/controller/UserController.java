package kr.re.rcbk.controller;

import kr.re.rcbk.common.authority.JwtFilter;
import kr.re.rcbk.common.response.BaseResponse;
import kr.re.rcbk.dto.LoginDto;
import kr.re.rcbk.dto.MemberDto;
import kr.re.rcbk.response.LoginResponse;
import kr.re.rcbk.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.LoginException;

@Slf4j
@RestController
@RequestMapping("/api/rcbk/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public BaseResponse<?> login(@RequestBody LoginDto loginDto) {

        try {
            LoginResponse res = userService.login(loginDto);


            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + res.getToken());

            Object data = ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(res);

            return BaseResponse.ok(data);

        }catch (Exception e){
            if(e instanceof LoginException){
                log.error("error : {}", e);
                return BaseResponse.error(HttpStatus.BAD_REQUEST,e);
            }else {
                log.error("error : {}", e);
                return BaseResponse.error(HttpStatus.BAD_REQUEST,e);
            }
        }

    }

    @PostMapping("/singUp")
    public BaseResponse<?> singUp(@RequestBody MemberDto memberDto) {

        try {
            String token = userService.singUp(memberDto);

            Object data = ResponseEntity.status(HttpStatus.CREATED).body(token);

            return BaseResponse.ok(data);

        }catch (Exception e){
            log.error(e.getMessage());
            return BaseResponse.error(HttpStatus.BAD_REQUEST,e);
        }

    }

}
