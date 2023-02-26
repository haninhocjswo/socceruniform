package shop.soccerUniform.entity.dto;

import lombok.Data;
import shop.soccerUniform.entity.enumtype.Grade;
import shop.soccerUniform.entity.enumtype.Role;
import shop.soccerUniform.entity.enumtype.UserState;

import java.util.List;

@Data
public class MemberSearchForm {

    private String searchKey;

    private String searchValue;

    private UserState state;

    private Grade grade;

    private Integer size;

    private Integer page;
}
