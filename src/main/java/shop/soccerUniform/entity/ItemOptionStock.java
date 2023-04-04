package shop.soccerUniform.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;
import shop.soccerUniform.entity.enumtype.UseYn;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "T_ITEM_OPTION_STOCK")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class ItemOptionStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_OPTION_STOCK_ID", nullable = false)
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
    private String sort;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UseYn useYn;

    public ItemOptionStock(Item item, ItemOptionValue firstOptionValue, ItemOptionValue secondOptionValue, String sort, Integer stock, String description) {
        this.item = item;
        this.firstOptionValue = firstOptionValue;
        this.secondOptionValue = secondOptionValue;
        this.sort = sort;
        this.stock = stock;
        this.description = description;
        this.useYn = UseYn.Y;
    }

    public void editItemOptionStock(ItemOptionValue firstOptionValue, ItemOptionValue secondOptionValue, String sort, Integer stock, String description, UseYn useYn) {
        this.firstOptionValue = firstOptionValue;
        this.secondOptionValue = secondOptionValue;
        this.sort = sort;
        this.stock = stock;
        this.description = description;
        this.useYn = UseYn.Y;
    }

    public void delItemOptionStock() {
        useYn = UseYn.N;
        stock = 0;
    }
}
