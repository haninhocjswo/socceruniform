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

    @Column(nullable = false)
    private String secondOptionName;

    @Column(nullable = false)
    private String secondOptionValueName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private Item item;

    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    public Cart(String itemName, String firstOptionName, String firstOptionValueName, String secondOptionName, String secondOptionValueName, Item item, Integer stock, Member member) {
        this.itemName = itemName;
        this.firstOptionName = firstOptionName;
        this.firstOptionValueName = firstOptionValueName;
        this.secondOptionName = secondOptionName;
        this.secondOptionValueName = secondOptionValueName;
        this.item = item;
        this.stock = stock;
        this.member = member;
    }
}
