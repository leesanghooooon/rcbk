package kr.re.rcbk.dto;

import kr.re.rcbk.common.Status.Role;
import lombok.*;

@ToString
@Data
@NoArgsConstructor
public class MemberRoleDto {

    private Role role;

    private MemberDto member;

    @Builder
    private MemberRoleDto(Role role, MemberDto member) {
        this.role = role;
        this.member = member;
    }
}