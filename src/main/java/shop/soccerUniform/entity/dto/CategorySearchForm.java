package shop.soccerUniform.entity.dto;

import lombok.Data;
import shop.soccerUniform.entity.enumtype.CategoryState;

@Data
public class CategorySearchForm {

    private String searchKey;

    private String searchValue;

    private Integer depth;

    private CategoryState state;

    private Integer page;

    private Integer size;
}
