package shop.soccerUniform.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;
import shop.soccerUniform.entity.enumtype.ItemState;
import shop.soccerUniform.entity.enumtype.OptionType;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "T_ITEM")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Item extends DateColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MANAGER_ID")
    private Manager manager;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @OneToMany(mappedBy = "item")
    private List<ItemOption> itemOptions = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<ItemOptionValue> itemOptionValues = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    public List<ItemOptionStock> itemOptionStocks = new ArrayList<>();

    private String manufacturer;

    private String origin;

    @Lob
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OptionType optionType;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemState state;

    public Item(String name, Manager manager, Category category, String manufacturer, String origin, String description, OptionType optionType, Integer price, ItemState state) {
        this.name = name;
        this.manager = manager;
        this.category = category;
        this.manufacturer = manufacturer;
        this.origin = origin;
        this.description = description;
        this.optionType = optionType;
        this.price = price;
        this.state = state;
    }

    public void editItem(String name, Manager manager, Category category, String manufacturer, String origin, String description, Integer price, ItemState state, OptionType optionType) {
        this.name = name;
        this.manager = manager;
        this.category = category;
        this.manufacturer = manufacturer;
        this.origin = origin;
        this.description = description;
        this.price = price;
        this.state = state;
        this.optionType = optionType;
    }

    public void deleteItem() {
        state = ItemState.DELETE;
    }
}
