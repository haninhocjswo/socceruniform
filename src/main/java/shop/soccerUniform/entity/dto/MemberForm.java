package shop.soccerUniform.entity.dto;

import lombok.Data;
import org.hibernate.annotations.Type;
import shop.soccerUniform.entity.enumtype.Gender;
import shop.soccerUniform.entity.enumtype.Grade;
import shop.soccerUniform.entity.enumtype.Role;
import shop.soccerUniform.entity.enumtype.UserState;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class MemberForm {

    @NotNull
    private Long memberId;

    @NotBlank(message = "아이디는 필수값입니다.")
    private String loginId;

    private String password;

    private String passwordCheck;

    @NotBlank(message = "이름은 필수값입니다.")
    private String username;

    @NotBlank(message = "이메일은 필수값입니다.")
    private String email;

    @NotNull(message = "상태값은 필수값입니다.")
    private UserState state;

    @NotNull(message = "성별은 필수값입니다.")
    private Gender gender;

    @NotNull(message = "등급은 필수값입니다.")
    private Grade grade;

    @NotBlank(message = "휴대폰번호는 필수값입니다.")
    private String mobile;

    @NotBlank(message = "생년월일은 필수값입니다.")
    private String birth;

    private String homeNum;

    @NotNull(message = "널값이어선 안됩니다.")
    @Min(value = 0, message = "0보단 커야합니다.")
    private Integer point = 0;
}
