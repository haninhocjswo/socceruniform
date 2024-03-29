package shop.soccerUniform.entity.dto;

import lombok.Data;
import shop.soccerUniform.entity.enumtype.ItemState;
import shop.soccerUniform.entity.enumtype.OptionType;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
public class ItemSaveForm {

    @NotBlank(message = "상품명은 필수값입니다.")
    private String name;

    @NotNull(message = "가격은 필수값입니다.")
    private Integer price;

    @NotNull(message = "입점업체를 선택해주세요.")
    private Long managerId;

    @NotNull(message = "카테고리는 필수값입니다.")
    @Min(value = 1, message = "카테고리를 선택해주세요.")
    private Long categoryId;

    @NotBlank(message = "제조사는 필수값입니다.")
    private String manufacturer;

    @NotBlank(message = "원산지는 필수값입니다.")
    private String origin;

    @NotNull(message = "상품옵션 유형을 선택해주세요.")
    private OptionType optionType;

    @NotNull(message = "상품상태를 선택해주세요.")
    private ItemState state;

    private String description;

    private String firstOptionName;

    private String secondOptionName;

    private String option1Values;

    private String option2Values;

    private List<ItemOptionStockForm> itemStocks = new ArrayList<>();

    private List<String> strList = new ArrayList<>();
}
