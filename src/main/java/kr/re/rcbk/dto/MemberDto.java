package kr.re.rcbk.dto;

import kr.re.rcbk.common.Status.Role;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class MemberDto {

    private String userid;

    private String userpw;

    private String username;

    private String hp;

    private String email;

    private String gender;

    private String zipcode;

    private String address1;

    private String address2;

    private String address3;

    private String regdate;

    private Role role;

}
