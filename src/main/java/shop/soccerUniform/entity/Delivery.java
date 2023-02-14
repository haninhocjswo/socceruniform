package shop.soccerUniform.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.soccerUniform.entity.enumtype.DeliveryCompany;
import shop.soccerUniform.entity.enumtype.DeliveryState;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "T_DELIVERY")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Delivery extends DateColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DELIVERY_ID")
    private Long id;

    @Column(nullable = false, name = "DELIVERY_NUM")
    private Integer deliveryNum;

    @Column(nullable = false, name = "DELIVERY_COMPANY")
    @Enumerated(EnumType.STRING)
    private DeliveryCompany deliveryCompany;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DeliveryState state;

    @Column(nullable = false, name = "RECEIVE_NAME")
    private String receiveNum;

    @Column(nullable = false)
    private String post;

    @Column(nullable = false)
    private String addr;

    @Column(nullable = false, name = "DETAIL_ADDR")
    private String detailAddr;
}
