package shop.soccerUniform.entity.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class OrderReceiveForm {
    private String req;
    private Long memberId;
    private Long itemId;
    private Integer totalPrice;
    private Set<String> selectedItems = new HashSet<>();
    private Set<Long> cartIds = new HashSet<>();
}
