package shop.soccerUniform.entity.dto;

import lombok.Builder;
import lombok.Data;
import shop.soccerUniform.entity.enumtype.DeliveryPolicy;
import shop.soccerUniform.entity.enumtype.Role;
import shop.soccerUniform.entity.enumtype.UserState;

@Data
public class ManagerDTO {

    private Long managerId;

    private String loginId;

    private String password;

    private String username;

    private String email;

    private Role role;

    private UserState state;

    private Integer post;

    private String address;

    private String detailAddress;

    private String businessResistNum;

    private String ceoName;

    private String chargeMobile;

    private String chargeName;

    private String chargeNum;

    private String chargePosition;

    private String companyName;

    private String companyNum;

    private DeliveryPolicy deliveryPolicy;

    private Double feePolicyPercent;
}
