package shop.soccerUniform.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.soccerUniform.entity.enumtype.UseYn;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "T_ITEM_OPTION_VALUE")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class ItemOptionValue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_OPTION_VALUE_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "ITEM_OPTION_ID")
    private ItemOption itemOption;

    @Column(name = "OPTION_VALUE", nullable = false)
    private String optionValue;

    @Column(name = "OPTION_VALUE_SORT", nullable = false)
    private Integer optionValueSort;

    @Enumerated(EnumType.STRING)
    @Column(name = "USE_YN", nullable = false)
    private UseYn useYn;

    public ItemOptionValue(Item item, ItemOption itemOption, String optionValue, Integer optionValueSort, UseYn useYn) {
        this.item = item;
        this.itemOption = itemOption;
        this.optionValue = optionValue;
        this.optionValueSort = optionValueSort;
        this.useYn = useYn;
    }

    public void editItemOptionValue(String optionValue, Integer optionValueSort) {
        this.optionValue = optionValue;
        this.optionValueSort = optionValueSort;
    }

    public void delItemOptionValue() {
        useYn = UseYn.N;
    }
}
