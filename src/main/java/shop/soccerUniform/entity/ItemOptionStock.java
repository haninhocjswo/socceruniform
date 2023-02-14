package shop.soccerUniform.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "T_ITEM_OPTION_STOCK")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class ItemOptionStock extends DateColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_OPTION_STOCK", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FIRST_ITEM_OPTION_ID")
    private ItemOption firstItemOption;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SECOND_ITEM_OPTION_ID")
    private ItemOption secondItemOption;

    @Column(nullable = false)
    private Integer stock;
}
