package shop.soccerUniform.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "T_ITEM_OPTION_STOCK")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class ItemOptionStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_OPTION_STOCK", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FIRST_OPTION_VALUE_ID")
    private ItemOptionValue firstOptionValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SECOND_OPTION_VALUE_ID")
    private ItemOptionValue secondOptionValue;

    @Column(nullable = false)
    private Integer stock;

    public ItemOptionStock(Item item, ItemOptionValue firstOptionValue, ItemOptionValue secondOptionValue, Integer stock) {
        this.item = item;
        this.firstOptionValue = firstOptionValue;
        this.secondOptionValue = secondOptionValue;
        this.stock = stock;
    }
}
