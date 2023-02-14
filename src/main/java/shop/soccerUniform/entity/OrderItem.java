package shop.soccerUniform.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.soccerUniform.entity.enumtype.OrderState;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "T_ORDER_ITEM")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class OrderItem extends DateColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private Item item;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderState state;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, name = "SALE_PRICE")
    private Integer salePrice;

    @Column(nullable = false, name = "EVENT_SALE_PRICE")
    private Integer eventSalePrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EVENT_ID", nullable = true)
    private Event event;

    @Column(nullable = false, name = "POINT_SALE_PRICE")
    private Integer pointSalePrice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POINT_ID", nullable = true)
    private Point point;

    public OrderItem(Order order, Item item, OrderState state, Integer price, Integer quantity, Integer salePrice, Integer eventSalePrice, Event event, Integer pointSalePrice, Point point) {
        this.order = order;
        this.item = item;
        this.state = state;
        this.price = price;
        this.quantity = quantity;
        this.salePrice = salePrice;
        this.eventSalePrice = eventSalePrice;
        this.event = event;
        this.pointSalePrice = pointSalePrice;
        this.point = point;
    }
}
