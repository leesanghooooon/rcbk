package kr.re.rcbk.common.Status;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;

@ToString
@Getter
public enum Role {
    ROLE_USER("ROLE_USER") ,
    ROLE_ADMIN("ROLE_ADMIN");

    String role;

    Role(String role){
        this.role = role;
    }

    @JsonValue
    public String value(){
        return role;
    }

    @JsonCreator
    public static Role parsing(String inputValue){
        return Arrays.stream(Role.values()).filter(type
                -> type.value().equals(inputValue)).findFirst().orElse(null);
    }


}
