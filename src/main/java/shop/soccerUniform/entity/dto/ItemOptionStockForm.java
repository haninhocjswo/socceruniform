package shop.soccerUniform.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ItemOptionStockForm {

    private Long itemOptionStockId;

    private Long itemId;

    @NotNull(message = "첫번째 옵션값은 필수값입니다.")
    private Long firstItemOptionId;

    private Long secondItemOptionId;

    @NotNull(message = "추가금액은 필수값입니다.")
    private Integer addPrice;

    @NotNull(message = "수량은 필수값입니다.")
    private Integer stock;
}
