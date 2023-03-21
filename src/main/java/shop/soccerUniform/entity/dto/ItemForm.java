package shop.soccerUniform.entity.dto;

import lombok.Data;
import shop.soccerUniform.entity.*;
import shop.soccerUniform.entity.enumtype.ItemState;
import shop.soccerUniform.entity.enumtype.OptionType;

import java.util.ArrayList;
import java.util.List;

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

    private Integer orderStock;

    private List<ItemOption> itemOptions = new ArrayList<>();

    private List<ItemOptionValue> itemOptionValues = new ArrayList<>();

    private ItemOptionStock itemOptionStock;
}
