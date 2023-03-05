package shop.soccerUniform.entity.dto;

import lombok.Data;
import shop.soccerUniform.entity.Category;
import shop.soccerUniform.entity.ItemOption;
import shop.soccerUniform.entity.Manager;
import shop.soccerUniform.entity.enumtype.ItemState;
import shop.soccerUniform.entity.enumtype.OptionType;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
public class ItemForm {

    private Long itemId;

    @NotEmpty(message = "상품명은 필수값입니다.")
    private String name;

    @NotEmpty(message = "입점업체를 선택해주세요.")
    private Manager manager;

    @NotEmpty(message = "카테고리를 선택해주세요.")
    private Category category;

    @NotEmpty(message = "제조사는 필수값입니다.")
    private String manufacturer;

    private String origin;

    private String description;

    @NotEmpty(message = "상품옵션 유형을 선택해주세요.")
    private OptionType optionType;


    private Integer price;

    @NotEmpty(message = "상품상태를 선택해주세요.")
    private ItemState state;

    private List<ItemOption> itemOptions = new ArrayList<>();
}
