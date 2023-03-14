package shop.soccerUniform.entity.dto;

import lombok.Data;
import shop.soccerUniform.entity.enumtype.Gender;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MemberSaveForm {
    @NotBlank(message = "아이디는 필수값입니다.")
    private String loginId;

    @NotBlank(message = "비밀번호는 필수값입니다.")
    private String password;

    @NotBlank(message = "이름은 필수값입니다.")
    private String username;

    @NotBlank(message = "이메일은 필수값입니다.")
    private String email;

    @NotBlank(message = "핸드폰번호는 필수값입니다.")
    private String mobile;

    private String homeNum;

    @NotBlank(message = "생년월일 필수값입니다.")
    private String birth;

    @NotNull(message = "우편번호는 필수값입니다.")
    private Integer post;

    @NotBlank(message = "주소는 필수값입니다.")
    private String addr;

    @NotBlank(message = "상세주소는 필수값입니다.")
    private String detailAddr;

    @NotNull(message = "성별은 필수값입니다.")
    private Gender gender;

    private boolean duplicatedLoginId = false;
}
