package shop.soccerUniform.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "T_ITEM_OPTION")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class ItemOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_OPTION_ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private Item item;

    @Column(name = "OPTION_NAME", nullable = false)
    private String optionName;

    @Column(name = "OPTION_SORT", nullable = false)
    private Integer optionSort;

    public ItemOption(Item item, String optionName, Integer optionSort) {
        this.item = item;
        this.optionName = optionName;
        this.optionSort = optionSort;
    }
}
