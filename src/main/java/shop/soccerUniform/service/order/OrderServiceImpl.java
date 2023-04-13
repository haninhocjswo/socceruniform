package shop.soccerUniform.service.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.soccerUniform.entity.*;
import shop.soccerUniform.entity.dto.ItemForm;
import shop.soccerUniform.entity.dto.OrderForm;
import shop.soccerUniform.entity.dto.OrderItemForm;
import shop.soccerUniform.entity.dto.OrderReceiveForm;
import shop.soccerUniform.repository.address.AddressRepository;
import shop.soccerUniform.repository.cart.CartRepository;
import shop.soccerUniform.repository.item.ItemRepository;
import shop.soccerUniform.repository.itemOptionStock.ItemOptionStockRepository;
import shop.soccerUniform.repository.itemOptionValue.ItemOptionValueRepository;
import shop.soccerUniform.repository.member.MemberRepository;
import shop.soccerUniform.repository.order.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService{

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final ItemOptionStockRepository itemOptionStockRepository;
    private final MemberRepository memberRepository;

    @Override
    public OrderForm receivedItem(OrderReceiveForm orderReceiveForm) {
        OrderForm orderForm = new OrderForm();
        if(orderReceiveForm.getCartIds().size() > 0) {
            Member member = memberRepository.findById(orderReceiveForm.getMemberId())
                    .orElseThrow(() -> new RuntimeException("존재하는 회원이 아닙니다."));
            orderForm.setMember(member);

            List<OrderItemForm> orderItemForms = new ArrayList<>();
            List<Cart> carts = cartRepository.findInCartId(orderReceiveForm.getCartIds());
            for (Cart cart : carts) {
                OrderItemForm orderItemForm = new OrderItemForm();
                orderItemForm.setQuantity(cart.getStock());
                Item item = itemRepository.findById(orderReceiveForm.getItemId())
                        .orElseThrow(() -> new RuntimeException("상품이 판매중이 아닙니다."));
                orderItemForm.setItem(item);
                orderItemForm.setPrice(item.getPrice() * cart.getStock());
                ItemOptionStock itemOptionStock = itemOptionStockRepository.findById(cart.getItemOptionStock().getId())
                        .orElseThrow(() -> new RuntimeException("상품의 재고가 없습니다."));
                orderItemForm.setItemOptionStock(itemOptionStock);
                orderItemForms.add(orderItemForm);
            }
            orderForm.setOrderItemForms(orderItemForms);
        }

        return orderForm;
    }

    @Override
    public OrderForm receivedCart(OrderReceiveForm orderReceiveForm) {
        // TODO 상품에서 바로 주문할 때

        return null;
    }
}
