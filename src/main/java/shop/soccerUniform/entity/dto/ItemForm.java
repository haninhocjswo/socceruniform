package shop.soccerUniform.entity.dto;

import lombok.Data;
import shop.soccerUniform.entity.Category;
import shop.soccerUniform.entity.Manager;
import shop.soccerUniform.entity.enumtype.ItemState;
import shop.soccerUniform.entity.enumtype.OptionType;

@Data
public class ItemForm {

    private Long itemId;

    private Manager manager;

    private Category category;

    private Integer price;

    private String manufacturer;

    private String name;

    private String origin;

    private String description;

    private OptionType optionType;

    private ItemState state;
}
