package kr.re.rcbk.dto;

import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {

    private String userid;

    private String userpw;

}