package shop.soccerUniform.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ItemOptionForm {

    @NotNull
    private Long itemOptionForm;

    @NotNull
    private Long itemId;

    @NotBlank(message = "옵션명은 필수값입니다.")
    private String optionName;

    @NotBlank(message = "옵션순서는 필수값입니다.")
    private String optionSort;

    @NotBlank(message = "옵션명은 필수값입니다.")
    private String valueName;

    @NotBlank(message = "옵션순서는 필수값입니다.")
    private String valueSort;
}
