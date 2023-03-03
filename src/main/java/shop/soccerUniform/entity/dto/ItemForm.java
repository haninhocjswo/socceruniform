package shop.soccerUniform.entity.dto;

import lombok.Data;
import shop.soccerUniform.entity.Category;
import shop.soccerUniform.entity.ItemOption;
import shop.soccerUniform.entity.Manager;
import shop.soccerUniform.entity.enumtype.ItemState;
import shop.soccerUniform.entity.enumtype.OptionType;

import java.util.ArrayList;
import java.util.List;

@Data
public class ItemForm {

    private Long itemId;

    private String name;

    private Manager manager;

    private Category category;

    private String manufacturer;

    private String origin;

    private String description;

    private OptionType optionType;

    private Integer price;

    private ItemState state;

    private List<ItemOption> itemOptions = new ArrayList<>();
}
