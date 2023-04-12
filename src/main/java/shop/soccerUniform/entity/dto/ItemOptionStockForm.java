package shop.soccerUniform.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ItemOptionStockForm {

    private Long itemOptionStockId;

    private Long itemId;

    private Integer addPrice;

    private Integer stock;

    private String sort;

    private Long firstItemOptionId;

    private Long secondItemOptionId;

    private String firstOptionName;

    private String secondOptionName;

    private String firstOptionValueName;

    private String secondOptionValueName;

    private Integer firstOptionValueSort;

    private Integer secondOptionValueSort;

    private String description;
}
