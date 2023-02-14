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
    @JoinColumn(name = "MANAGER_ID")
    private Manager manager;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
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
}