package shop.soccerUniform.entity.dto;

import lombok.Data;
import shop.soccerUniform.entity.enumtype.UserState;

@Data
public class ManagerSearchForm {

    private String searchKey;

    private String searchValue;

    private UserState state;

    private Integer size;

    private Integer page;
}
