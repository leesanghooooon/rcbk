package kr.re.rcbk.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_]{5,20}",
            message = "영문, 숫자, _만을 사용한 5~20자리로 입력하세요")
    @Size(min = 5, max = 20,
            message = "5~20자 사이로 입력하세요.")
    private String loginId;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@$%^&*])[a-zA-Z0-9!@$%^&*]{8,20}",
            message = "영문, 숫자, 특수문자(!@$%^&*)만 입력 가능합니다.")
    @Size(min = 5, max = 20,
            message = "8~20자 사이로 입력하세요.")
    private String password;
}
