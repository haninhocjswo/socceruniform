package shop.soccerUniform.entity;

import lombok.Getter;
import shop.soccerUniform.entity.enumtype.DeliveryPolicy;

import javax.persistence.*;

@Entity
@Table(name = "T_MANAGER")
@Getter
@DiscriminatorValue("MANAGER")
public class Manager extends User {

    @Column(name = "COMPANY_NAME", nullable = false)
    private String companyName;

    @Column(name = "COMPANY_NUM")
    private String companyNum;

    @Column(nullable = false)
    private Integer post;

    @Column(nullable = false)
    private String address;

    @Column(name = "DETAIL_ADDRESS", nullable = false)
    private String detailAddress;

    @Column(name = "CEO_NAME", nullable = false)
    private String ceoName;

    @Column(name = "BUSINESS_RESIST_NUM", nullable = false)
    private String businessResistNum;

    @Column(name = "CHARGE_NAME", nullable = false)
    private String chargeName;

    @Column(name = "CHARGE_POSITION")
    private String chargePosition;

    @Column(name = "CHARGE_NUM", nullable = false)
    private String chargeNum;

    @Column(name = "CHARGE_MOBILE", nullable = false)
    private String chargeMobile;

    @Column(name = "FEE_POLICY_PERCENT", nullable = false)
    private Double feePolicyPercent;

    @Column(name = "DELIVERY_POLICY", nullable = false)
    private DeliveryPolicy deliveryPolicy;
}
