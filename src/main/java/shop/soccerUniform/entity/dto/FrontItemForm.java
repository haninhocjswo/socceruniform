package shop.soccerUniform.entity.dto;

import lombok.Data;
import shop.soccerUniform.entity.*;
import shop.soccerUniform.entity.enumtype.OptionType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class FrontItemForm {

    private Long itemId;

    private String name;

    private Integer price;

    private String origin;

    private String manufacturer;

    private String description;

    private Manager manager;

    private Category category;

    private OptionType optionType;

    private List<ItemOption> itemOptions = new ArrayList<>();

    private List<ItemOptionValue> itemOptionValues = new ArrayList<>();

    private List<ItemOptionStock> itemOptionStocks = new ArrayList<>();

    private Set<String> selectedItem = new HashSet<>();
}
