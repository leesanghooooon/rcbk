package kr.re.rcbk.controller;

import kr.re.rcbk.common.authority.JwtFilter;
import kr.re.rcbk.common.response.BaseResponse;
import kr.re.rcbk.dto.LoginDto;
import kr.re.rcbk.dto.UserDto;
import kr.re.rcbk.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.LoginException;

@Slf4j
@RestController
@RequestMapping("/api/rcbk/member")
public class MemberController {

    @Autowired
    MemberService memberService;


    @PostMapping("/getMember")
    public BaseResponse<?> getMemberInfo(LoginDto loginDto){
        LoginDto data = memberService.getMemberInfo(loginDto);
        return BaseResponse.ok(data);
    }


}
