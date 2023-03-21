package shop.soccerUniform.service.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.soccerUniform.entity.Item;
import shop.soccerUniform.entity.Member;
import shop.soccerUniform.entity.dto.OrderForm;
import shop.soccerUniform.entity.dto.OrderReceiveForm;
import shop.soccerUniform.repository.cart.CartRepository;
import shop.soccerUniform.repository.item.ItemRepository;
import shop.soccerUniform.repository.member.MemberRepository;
import shop.soccerUniform.repository.order.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;

    @Override
    public OrderForm receivedItem(OrderReceiveForm orderReceiveForm) {
        OrderForm orderForm = new OrderForm();

        Optional<Member> memberOptional = memberRepository.findById(orderReceiveForm.getMemberId());
        if(memberOptional.isEmpty()) return orderForm;
        orderForm.setMember(memberOptional.get());

        Optional<Item> itemOptional = itemRepository.findById(orderReceiveForm.getItemId());
        if(itemOptional.isEmpty()) return orderForm;
        Item item = itemOptional.get();
        //TODO ITEMOPTIONSTOCK 다시 만들어야함 DESCRIPTION 추가해야함 우선순위 1번째 -> 주문폼 작성해야함 우선순위 2번째

        orderForm.setResultFlag(true);

        return orderForm;
    }

    @Override
    public OrderForm receivedCart(OrderReceiveForm orderReceiveForm) {
        OrderForm orderForm = new OrderForm();
        return orderForm;
    }
}
