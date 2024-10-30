package kr.re.rcbk.service;

import kr.re.rcbk.common.Status.Role;
import kr.re.rcbk.common.authority.TokenProvider;
import kr.re.rcbk.config.PasswordEncoderConfig;
import kr.re.rcbk.dto.LoginDto;
import kr.re.rcbk.dto.MemberDto;
import kr.re.rcbk.dto.MemberRoleDto;
import kr.re.rcbk.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;
import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
@Configuration
public class UserService {

    @Autowired
    PasswordEncoderConfig passwordEncoder;

    private final TokenProvider tokenProvider;

    @Autowired
    MemberService memberService;

    @Autowired
    SqlSession sqlSession;

    /**
     * 로그인 로직
     * @param loginDto loginDto
     * @return String
     * @throws LoginException
     */
    public String login(LoginDto loginDto) throws LoginException {

        UserDto userDto = memberService.findByUserNo(loginDto.getUserid());
        if(ObjectUtils.isEmpty(userDto)) throw new LoginException("잘못된 아이디 입니다.");
        if (!passwordEncoder.passwordEncoder().matches(loginDto.getUserpw(), userDto.getUserpw())) {
            throw new LoginException("잘못된 비밀번호입니다.");
        }

        // Token Checked..
        String toke = tokenProvider.createToken(userDto.getUserid(), Collections.singletonList(userDto.getRole()));

        return tokenProvider.createToken(userDto.getUserid(), Collections.singletonList(userDto.getRole()));
    }

    public String singUp(MemberDto memberDto) throws Exception {

        UserDto userDto = memberService.findByUserNo(memberDto.getUserid());
        if(ObjectUtils.isNotEmpty(userDto)) throw new Exception("중복된 아이디 입니다.");

        memberDto.setUserpw(passwordEncoder.passwordEncoder().encode(memberDto.getUserpw()));

        memberDto.setRole(Role.ROLE_USER);
//        MemberRoleDto memberRoleDto = MemberRoleDto.builder()
//                .role(Role.MEMBER)
//                .member(memberDto)
//                .build();

        log.info("======= START");
        log.info("memberRoleDto : {}", memberDto.toString());
        log.info("======= END");

        sqlSession.insert("memberMapper.saveMember",memberDto);

        return "SUCCESS";

    }

}
