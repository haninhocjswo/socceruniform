package shop.soccerUniform.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import shop.soccerUniform.entity.Item;
import shop.soccerUniform.entity.ItemOptionStock;

@Data
@NoArgsConstructor
public class CartForm {
    private Long cartId;
    private Long memberId;
    private Item item;
    private ItemOptionStock itemOptionStock;
    private String description;
}
