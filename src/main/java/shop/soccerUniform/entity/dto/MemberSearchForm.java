package shop.soccerUniform.entity.dto;

import lombok.Data;
import shop.soccerUniform.entity.enumtype.Grade;
import shop.soccerUniform.entity.enumtype.Role;
import shop.soccerUniform.entity.enumtype.UserState;

@Data
public class MemberSearchForm {

    private String searchKey;

    private String searchValue;

    private UserState state;

    private Grade grade;

    private Integer offset = 0;

    private Integer limit = 10;
}
