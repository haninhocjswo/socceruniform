package shop.soccerUniform.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "T_ACCOUNT_ITEM")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class AccountItem extends DateColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ITEM_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ITEM_ID", nullable = false)
    private OrderItem orderItem;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_CANCEL_ID")
    private OrderItemCancel orderItemCancel;

    @Column(name = "PAYMENT_AMOUNT", nullable = false)
    private Integer paymentAmount;

    @Column(name = "RESULT_AMOUNT", nullable = false)
    private Integer resultAmount;

    @Column(name = "FEE", nullable = false)
    private Integer fee;

    public AccountItem(Account account, OrderItem orderItem, OrderItemCancel orderItemCancel, Integer paymentAmount, Integer resultAmount, Integer fee) {
        this.account = account;
        this.orderItem = orderItem;
        this.orderItemCancel = orderItemCancel;
        this.paymentAmount = paymentAmount;
        this.resultAmount = resultAmount;
        this.fee = fee;
    }
}
