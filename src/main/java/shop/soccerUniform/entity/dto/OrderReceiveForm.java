package shop.soccerUniform.entity.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class OrderReceiveForm {
    private String req;
    private Long memberId;
    private Long itemId;
    private Integer totalPrice;
    private List<String> selectedItems = new ArrayList<>();
    private List<Long> cartIds = new ArrayList<>();
}
