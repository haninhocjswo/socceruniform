package shop.soccerUniform.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "T_ORDER_ITEM_CANCEL")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class OrderItemCancel extends DateColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ITEM_CANCEL_ID")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ITEM_ID", nullable = false)
    private OrderItem orderItem;

    @Column(name = "CANCEL_QUANTITY", nullable = false)
    private Integer cancelQuantity;

    @Column(name = "CANCEL_PRICE", nullable = false)
    private Integer cancelPrice;

    @Column(name = "CANCEL_SALE_PRICE", nullable = false)
    private Integer cancelSalePrice;

    @Column(name = "CANCEL_EVENT_SALE_PRICE", nullable = false)
    private Integer cancelEventSalePrice;

    @Column(name = "CANCEL_POINT_SALE_PRICE", nullable = false)
    private Integer cancelPointSalePrice;
}
