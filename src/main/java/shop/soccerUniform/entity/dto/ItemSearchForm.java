package shop.soccerUniform.entity.dto;

import lombok.Data;
import shop.soccerUniform.entity.Category;
import shop.soccerUniform.entity.enumtype.ItemState;

@Data
public class ItemSearchForm {

    private String searchKey;

    private String searchValue;

    private ItemState state;

    private Long managerId;

    private Integer size;

    private Integer page;
}
