package shop.soccerUniform.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import shop.soccerUniform.entity.Item;
import shop.soccerUniform.entity.ItemOptionStock;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class CartForm {
    private Long cartId;
    private Long memberId;
    private Long itemId;
    private Item item;
    private String description;
    private Integer stock;
    private Integer price;
    private Set<String> selectedItems = new HashSet<>();
}
