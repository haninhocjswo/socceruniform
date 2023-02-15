package shop.soccerUniform.entity.dto;

import lombok.Data;
import shop.soccerUniform.entity.enumtype.Gender;
import shop.soccerUniform.entity.enumtype.Grade;
import shop.soccerUniform.entity.enumtype.Role;
import shop.soccerUniform.entity.enumtype.UserState;

@Data
public class MembersDTO {

    private Long memberId;

    private String loginId;

    private String username;

    private Role role;

    private UserState state;

    private String mobile;

    private Integer point;

    private Grade grade;
}
