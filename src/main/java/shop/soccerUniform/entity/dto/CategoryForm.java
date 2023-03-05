package shop.soccerUniform.entity.dto;

import lombok.Data;
import shop.soccerUniform.entity.enumtype.CategoryState;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class CategoryForm {
    private Long categoryId;

    @NotBlank
    private String name;

    @NotBlank
    @Min(value = 1)
    private Integer depth;

    private CategoryState state;

    private Long parentId;

    private String parentName;
}
