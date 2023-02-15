package shop.soccerUniform.entity.dto;

import lombok.Data;
import shop.soccerUniform.entity.enumtype.Gender;
import shop.soccerUniform.entity.enumtype.Grade;
import shop.soccerUniform.entity.enumtype.Role;
import shop.soccerUniform.entity.enumtype.UserState;

import javax.validation.constraints.NotEmpty;

@Data
public class MemberForm {

    private Long memberId;

    @NotEmpty
    private String loginId;

    private String password;

    @NotEmpty
    private String username;

    @NotEmpty
    private String email;

    private UserState state;

    private Gender gender;

    private Grade grade;

    @NotEmpty
    private String mobile;

    private String homeNum;

    private Integer point = 0;
}
