package kr.re.rcbk.common.authority;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.re.rcbk.dto.UserDto;
import kr.re.rcbk.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Service
//@AllArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MemberService memberService;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserDto> res = Optional.ofNullable(memberService.findByUserNo(username));
        return res
                .map(this::addAuthorities)
                .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));

    }

    private UserDto addAuthorities(UserDto userDto) {
        userDto.setAuthorities(List.of(new SimpleGrantedAuthority(userDto.getRole())));

        return userDto;
    }
}
