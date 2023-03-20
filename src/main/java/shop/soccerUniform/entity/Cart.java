package shop.soccerUniform.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "T_CART")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Cart extends DateColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CART_ID", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String itemName;

    @Column(nullable = false)
    private String firstOptionName;

    @Column(nullable = false)
    private String firstOptionValueName;

    private String secondOptionName;

    private String secondOptionValueName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_OPTION_STOCK_ID", nullable = false)
    private ItemOptionStock itemOptionStock;

    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    public Cart(String itemName, ItemOption firstItemOption, ItemOption secondItemOption, ItemOptionValue firstItemOptionValue,
                ItemOptionValue secondItemOptionValue, Item item, ItemOptionStock itemOptionStock, Integer stock, Member member) {
        this.itemName = itemName;
        if(firstItemOption != null) this.firstOptionName = firstItemOption.getOptionName();
        if(secondItemOption != null) this.secondOptionName = secondItemOption.getOptionName();
        if(firstItemOptionValue != null) this.firstOptionValueName = firstItemOptionValue.getOptionValue();
        if(secondItemOptionValue != null) this.secondOptionValueName = secondItemOptionValue.getOptionValue();
        this.item = item;
        this.itemOptionStock = itemOptionStock;
        this.stock = stock;
        this.member = member;
    }

    public void editStock(Integer stock) {
        this.stock = stock;
    }
}
