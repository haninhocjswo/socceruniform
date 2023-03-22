package shop.soccerUniform.service.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.soccerUniform.entity.*;
import shop.soccerUniform.entity.dto.ItemForm;
import shop.soccerUniform.entity.dto.OrderForm;
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

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final AddressRepository addressRepository;
    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final ItemOptionValueRepository itemOptionValueRepository;
    private final ItemOptionStockRepository itemOptionStockRepository;

    @Override
    public OrderForm receivedItem(OrderReceiveForm orderReceiveForm) {
        if(orderReceiveForm.getSelectedItems().size() == 0) return new OrderForm();

        Optional<Member> memberOptional = memberRepository.findById(orderReceiveForm.getMemberId());
        if(memberOptional.isEmpty()) return new OrderForm();

        List<Address> addresses = addressRepository.findByMemberId(memberOptional.get().getId());

        Set<String> selectedItems = orderReceiveForm.getSelectedItems();
        List<ItemForm> itemForms = new ArrayList<>();
        for (String selectedItem : selectedItems) {
            Optional<Item> itemOptional = itemRepository.findById(orderReceiveForm.getItemId());
            if(itemOptional.isEmpty()) return new OrderForm();
            Item item = itemOptional.get();
            ItemForm itemForm = new ItemForm();
            itemForm.setItemId(item.getId());
            itemForm.setName(item.getName());
            itemForm.setPrice(item.getPrice());
            itemForm.setCategory(item.getCategory());
            itemForm.setOrigin(item.getOrigin());
            itemForm.setManufacturer(item.getManufacturer());
            itemForm.setDescription(item.getDescription());
            itemForm.setOptionType(item.getOptionType());

            String[] infoSplit = selectedItem.split("_");
            if(infoSplit.length != 3) return new OrderForm();
            Optional<ItemOptionValue> firstItemOptionValueOptional = itemOptionValueRepository.findById(Long.parseLong(infoSplit[0]));
            if(firstItemOptionValueOptional.isEmpty()) return new OrderForm();
            Optional<ItemOptionValue> secondItemOptionValueOptional = itemOptionValueRepository.findById(Long.parseLong(infoSplit[1]));
            if(secondItemOptionValueOptional.isEmpty()) return new OrderForm();
            Optional<ItemOptionStock> itemOptionStockOptional =
                    itemOptionStockRepository.findByFirstOptionValueAndSecondOptionValue(firstItemOptionValueOptional.get(), secondItemOptionValueOptional.get());
            if(itemOptionStockOptional.isEmpty()) return new OrderForm();
            itemForm.setItemOptionStock(itemOptionStockOptional.get());
            itemForm.setOrderStock(Integer.parseInt(infoSplit[2]));

            itemForms.add(itemForm);
        }

        OrderForm orderForm = new OrderForm();
        orderForm.setMember(memberOptional.get());
        orderForm.setAddresses(addresses);
        orderForm.setItems(itemForms);
        orderForm.setResultFlag(true);

        return orderForm;
    }

    @Override
    public OrderForm receivedCart(OrderReceiveForm orderReceiveForm) {
        Optional<Member> memberOptional = memberRepository.findById(orderReceiveForm.getMemberId());
        if(memberOptional.isEmpty()) return new OrderForm();

        List<Address> addresses = addressRepository.findByMemberId(memberOptional.get().getId());

        Set<Long> cartIds = orderReceiveForm.getCartIds();
        if(cartIds.size() == 0) return new OrderForm();
        List<Cart> carts = cartRepository.findInCartId(cartIds);
        List<ItemForm> itemForms = new ArrayList<>();
        for (Cart cart : carts) {
            Optional<Item> itemOptional = itemRepository.findById(cart.getItem().getId());
            if(itemOptional.isEmpty()) return new OrderForm();
            Item item = itemOptional.get();
            ItemForm itemForm = new ItemForm();
            itemForm.setItemId(item.getId());
            itemForm.setName(item.getName());
            itemForm.setPrice(item.getPrice());
            itemForm.setCategory(item.getCategory());
            itemForm.setOrigin(item.getOrigin());
            itemForm.setManufacturer(item.getManufacturer());
            itemForm.setDescription(item.getDescription());
            itemForm.setOptionType(item.getOptionType());
            itemForm.setOrderStock(cart.getStock());

            Optional<ItemOptionStock> itemOptionStockOptional = itemOptionStockRepository.findById(cart.getItemOptionStock().getId());
            if(itemOptionStockOptional.isEmpty()) return new OrderForm();
            itemForm.setItemOptionStock(itemOptionStockOptional.get());

            itemForms.add(itemForm);
        }

        OrderForm orderForm = new OrderForm();
        orderForm.setMember(memberOptional.get());
        orderForm.setAddresses(addresses);
        orderForm.setItems(itemForms);
        orderForm.setResultFlag(true);

        return orderForm;
    }
}
