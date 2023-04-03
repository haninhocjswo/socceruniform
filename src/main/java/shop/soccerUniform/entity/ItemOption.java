package shop.soccerUniform.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.soccerUniform.entity.enumtype.UseYn;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "itemOption")
    private List<ItemOptionValue> itemOptionValues = new ArrayList<>();

    @Column(name = "OPTION_NAME", nullable = false)
    private String optionName;

    @Column(name = "OPTION_SORT", nullable = false)
    private Integer optionSort;

    @Column(name = "USE_YN", nullable = false)
    private UseYn useYn;

    public ItemOption(Item item, String optionName, Integer optionSort, UseYn useYn) {
        this.item = item;
        this.optionName = optionName;
        this.optionSort = optionSort;
        this.useYn = useYn;
    }

    public void editOption(String optionName) {
        this.optionName = optionName;
    }

    public void delOption(UseYn useYn) {
        this.useYn = useYn;
    }
}
