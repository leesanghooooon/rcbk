package kr.re.rcbk.service;

import kr.re.rcbk.common.authority.TokenProvider;
import kr.re.rcbk.config.PasswordEncoderConfig;
import kr.re.rcbk.dto.LoginDto;
import kr.re.rcbk.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.security.auth.login.LoginException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Collections;
import java.util.Optional;

@Slf4j
@Service
public class MemberService {


    @Autowired
    SqlSession sqlSession;


    public LoginDto getMemberInfo(LoginDto loginDto) {

        LoginDto member = sqlSession.selectOne("memberMapper.getSelectOneMemberInfo", loginDto);
        log.info("member.. {}", member.toString());
        return member;
    }

    /**
     * 유저 조회
     * @param userid 회원ID
     * @return UserDto
     */
    public UserDto findByUserNo(String userid) {
        UserDto user = sqlSession.selectOne("memberMapper.findUserByUsername", userid);
        return user;
    }

}
