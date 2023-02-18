package shop.soccerUniform.entity.dto;

import lombok.Data;
import shop.soccerUniform.entity.enumtype.BoardType;

import javax.validation.constraints.NotEmpty;

@Data
public class BoardSearchForm {

    private String searchKey;
    private String searchValue;
    private Integer offset = 0;
    private Integer limit = 10;

    private BoardType boardType;
}
