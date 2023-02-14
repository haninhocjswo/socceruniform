package shop.soccerUniform.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "T_ACCOUNT")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Account extends DateColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ACCOUNT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MANAGER_ID", nullable = false)
    private Manager manager;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private Order order;

    @Column(nullable = false)
    private Integer year;

    @Column(nullable = false)
    private Integer month;

    @Column(nullable = false)
    private Integer day;

    @Column(nullable = false, name = "TOTAL_PAYMENT_AMOUNT")
    private Integer totalPaymentAmount;

    @Column(nullable = false, name = "TOTAL_RESULT_AMOUNT")
    private Integer totalResultAmount;

    @Column(nullable = false, name = "TOTAL_FEE")
    private Integer totalFee;

    public Account(Manager manager, Order order, Integer year, Integer month, Integer day, Integer totalPaymentAmount, Integer totalResultAmount, Integer totalFee) {
        this.manager = manager;
        this.order = order;
        this.year = year;
        this.month = month;
        this.day = day;
        this.totalPaymentAmount = totalPaymentAmount;
        this.totalResultAmount = totalResultAmount;
        this.totalFee = totalFee;
    }
}