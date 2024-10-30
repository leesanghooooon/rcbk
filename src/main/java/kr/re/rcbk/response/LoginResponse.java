package kr.re.rcbk.response;

import lombok.Data;

@Data
public class LoginResponse {

    private String userid;

    private String username;

    private String token;
}
