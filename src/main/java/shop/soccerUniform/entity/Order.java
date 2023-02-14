package shop.soccerUniform.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.soccerUniform.entity.enumtype.OrderState;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "T_ORDER")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Order extends DateColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @Column(nullable = false, name = "ORDER_NUM")
    private String OrderNum;

    @Column(nullable = false, name = "TOTAL_SALE_PRICE")
    private Integer totalSalePrice;

    @Column(nullable = false, name = "TOTAL_EVENT_SALE_PRICE")
    private Integer totalEventSalePrice;

    @Column(nullable = false, name = "TOTAL_POINT_SALE_PRICE")
    private Integer totalPointSalePrice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private Integer totalPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderState state;
}
