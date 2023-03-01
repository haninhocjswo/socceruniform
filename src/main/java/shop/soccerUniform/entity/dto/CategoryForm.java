package shop.soccerUniform.entity.dto;

import lombok.Data;
import shop.soccerUniform.entity.enumtype.CategoryState;

@Data
public class CategoryForm {
    private Long categoryId;
    private String name;
    private Integer depth;
    private CategoryState state;
    private Long parentId;
    private String parentName;
}
