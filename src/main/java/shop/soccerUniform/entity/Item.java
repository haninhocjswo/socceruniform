package shop.soccerUniform.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.soccerUniform.entity.enumtype.ItemState;
import shop.soccerUniform.entity.enumtype.OptionType;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MANAGER_ID")
    private Manager manager;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

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

    public Item(Manager manager, Category category, String manufacturer, String origin, String description, OptionType optionType, Integer price, ItemState state) {
        this.manager = manager;
        this.category = category;
        this.manufacturer = manufacturer;
        this.origin = origin;
        this.description = description;
        this.optionType = optionType;
        this.price = price;
        this.state = state;
    }
}
