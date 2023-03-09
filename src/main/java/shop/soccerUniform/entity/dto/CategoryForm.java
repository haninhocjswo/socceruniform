package shop.soccerUniform.entity.dto;

import lombok.Data;
import shop.soccerUniform.entity.enumtype.CategoryState;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CategoryForm {
    private Long categoryId;

    @NotBlank(message = "카테고리명은 필수값입니다.")
    private String name;

    @NotNull(message = "뎁스는 필수값입니다.")
    @Min(value = 1, message = "뎁스는 0보다 커야합니다.")
    private Integer depth;

    @NotNull(message = "상태값은 필수입니다.")
    private CategoryState state;

    @NotNull(message = "상위카테고리는 필수입니다.")
    @Min(value = 1, message = "상위카테고리를 선택해주세요.")
    private Long parentId;

    private String parentName;
}
