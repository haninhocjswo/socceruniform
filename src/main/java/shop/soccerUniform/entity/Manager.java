package shop.soccerUniform.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.soccerUniform.entity.dto.ManagerDTO;
import shop.soccerUniform.entity.enumtype.DeliveryPolicy;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "T_MANAGER")
@Getter
@Builder
@DiscriminatorValue("MANAGER")
@NoArgsConstructor(access = PROTECTED)
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

    @Column(name = "CHARGE_NUM")
    private String chargeNum;

    @Column(name = "CHARGE_MOBILE", nullable = false)
    private String chargeMobile;

    @Column(name = "FEE_POLICY_PERCENT")
    private Double feePolicyPercent;

    @Column(name = "DELIVERY_POLICY")
    @Enumerated(EnumType.STRING)
    private DeliveryPolicy deliveryPolicy;

    public Manager(String companyName, String companyNum, Integer post, String address, String detailAddress, String ceoName, String businessResistNum, String chargeName, String chargePosition, String chargeNum, String chargeMobile, Double feePolicyPercent, DeliveryPolicy deliveryPolicy) {
        this.companyName = companyName;
        this.companyNum = companyNum;
        this.post = post;
        this.address = address;
        this.detailAddress = detailAddress;
        this.ceoName = ceoName;
        this.businessResistNum = businessResistNum;
        this.chargeName = chargeName;
        this.chargePosition = chargePosition;
        this.chargeNum = chargeNum;
        this.chargeMobile = chargeMobile;
        this.feePolicyPercent = feePolicyPercent;
        this.deliveryPolicy = deliveryPolicy;
    }

    public void editManager(ManagerDTO managerDTO) {
        companyName = managerDTO.getCompanyName();
        companyNum = managerDTO.getChargeNum();
        post = managerDTO.getPost();
        address = managerDTO.getAddress();
        detailAddress = managerDTO.getDetailAddress();
        ceoName = managerDTO.getCeoName();
        businessResistNum = managerDTO.getBusinessResistNum();
        chargeName = managerDTO.getChargeName();
        chargePosition = managerDTO.getChargePosition();
        chargeNum = managerDTO.getChargeNum();
        chargeMobile = managerDTO.getChargeMobile();
        feePolicyPercent = managerDTO.getFeePolicyPercent();
        deliveryPolicy = managerDTO.getDeliveryPolicy();
    }
}
