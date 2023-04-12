package shop.soccerUniform.service.cart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import shop.soccerUniform.entity.*;
import shop.soccerUniform.entity.dto.CartForm;
import shop.soccerUniform.entity.enumtype.OptionType;
import shop.soccerUniform.repository.cart.CartRepository;
import shop.soccerUniform.repository.item.ItemRepository;
import shop.soccerUniform.repository.itemOption.ItemOptionRepository;
import shop.soccerUniform.repository.itemOptionStock.ItemOptionStockRepository;
import shop.soccerUniform.repository.itemOptionValue.ItemOptionValueRepository;
import shop.soccerUniform.repository.member.MemberRepository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final ItemOptionRepository itemOptionRepository;
    private final ItemOptionValueRepository itemOptionValueRepository;
    private final ItemOptionStockRepository itemOptionStockRepository;

    @Transactional
    @Override
    public boolean saveCart(CartForm cartForm) {
        Member member = memberRepository.findById(cartForm.getMemberId()).orElseThrow(() -> new RuntimeException("회원이 존재하지 않습니다."));

        if(cartForm.getSelectedItems().length < 1) return false;
        Item item = itemRepository.findById(cartForm.getItemId()).orElseThrow(() -> new RuntimeException("해당 상품은 판매중인 상품이 아닙니다."));

        List<ItemOption> itemOptions = itemOptionRepository.findByItemId(cartForm.getItemId());
        if(itemOptions.size() < 1) return false;
        ItemOption firstOption = itemOptions.get(0);
        ItemOption secondOption = null;
        if(item.getOptionType() == OptionType.DOUBLE) {
            if(itemOptions.size() != 2) return false;
            secondOption = itemOptions.get(1);
        }

        List<ItemOptionValue> itemOptionValues = itemOptionValueRepository.findByItemId(cartForm.getItemId());
        if(itemOptionValues.size() < 1) return false;
        Map<Long, ItemOptionValue> itemOptionValueMap = itemOptionValues.stream()
                .collect(Collectors.toMap(ItemOptionValue::getId, Function.identity()));
        for(String selectedItem : cartForm.getSelectedItems()) {
            ItemOptionValue secondItemOptionValue = null;
            String[] selectedItemSplit = selectedItem.split("_");
            ItemOptionValue firstItemOptionValue = itemOptionValueMap.get(Long.parseLong(selectedItemSplit[0]));
            if(!selectedItemSplit[1].equals("0")) secondItemOptionValue = itemOptionValueMap.get(Long.parseLong(selectedItemSplit[1]));
            ItemOptionStock itemOptionStock = itemOptionStockRepository
                    .findByFirstOptionValueAndSecondOptionValue(firstItemOptionValue, secondItemOptionValue)
                    .orElseThrow(() -> new RuntimeException("상품 재고의 데이터가 존재하지 않습니다."));

            Cart cart = new Cart(item.getName(), firstOption, secondOption, firstItemOptionValue, secondItemOptionValue, item,
                    itemOptionStock, Integer.parseInt(selectedItemSplit[2]), member);
            cart.addDate(LocalDateTime.now(), LocalDateTime.now());
            cartRepository.save(cart);
        }

        return true;
    }

    @Transactional
    @Override
    public boolean editCart(Long cartId, Integer stock) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if(cartOptional.isEmpty()) return false;
        Cart cart = cartOptional.get();
        cart.editStock(stock);

        return true;
    }

    @Transactional
    @Override
    public boolean delCart(Long cartId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        if(cartOptional.isEmpty()) return false;
        Cart cart = cartOptional.get();
        cartRepository.delete(cart);

        return true;
    }

    @Override
    public boolean overlapCheck(CartForm cartForm) {
        for(String description : cartForm.getSelectedItems()) {
            String[] descriptionSplit = description.split("_");

            Optional<ItemOptionValue> firstOptionValueOptional = itemOptionValueRepository.findById(Long.parseLong(descriptionSplit[0]));
            if(firstOptionValueOptional.isEmpty()) return false;
            ItemOptionValue firstOptionValue = firstOptionValueOptional.get();
            Optional<ItemOptionValue> secondOptionValueOptional = itemOptionValueRepository.findById(Long.parseLong(descriptionSplit[1]));
            ItemOptionValue secondOptionValue = null;
            if(secondOptionValueOptional.isPresent()) secondOptionValue = secondOptionValueOptional.get();

            Optional<ItemOptionStock> itemOptionStockOptional =
                    itemOptionStockRepository.findByFirstOptionValueAndSecondOptionValue(firstOptionValue, secondOptionValue);
            if(itemOptionStockOptional.isEmpty()) return false;
            ItemOptionStock itemOptionStock = itemOptionStockOptional.get();

            Long countCart = cartRepository.cartFindOverlap(cartForm.getMemberId(), cartForm.getItemId(), itemOptionStock.getId());
            if(countCart > 0) return false;
        }
        return true;
    }

    @Override
    public List<CartForm> findCartsByLoginId(String loginId) {
        Optional<Member> memberOptional = memberRepository.findByLoginId(loginId);
        List<CartForm> carts = new ArrayList<>();
        if(memberOptional.isEmpty()) return carts;

        Member member = memberOptional.get();
        List<Cart> findCarts = cartRepository.findByMemberId(member.getId());
        if(findCarts.size() == 0) return carts;

        for (Cart cart : findCarts) {
            CartForm cartForm = new CartForm();
            cartForm.setCartId(cart.getId());
            cartForm.setItem(cart.getItem());
            cartForm.setItemId(cart.getItem().getId());
            cartForm.setMemberId(member.getId());
            String description = cart.getItemName() + "(" + cart.getFirstOptionName() + " : " + cart.getFirstOptionValueName() + ")";
            if(StringUtils.hasText(cart.getSecondOptionName()) && StringUtils.hasText(cart.getSecondOptionValueName())) {
                description = cart.getItemName() + "(" + cart.getFirstOptionName() + " : " + cart.getFirstOptionValueName() +
                        " | " + cart.getSecondOptionName() + " : " + cart.getSecondOptionValueName() + ")";
            }
            cartForm.setDescription(description);
            cartForm.setStock(cart.getStock());
            cartForm.setOnePrice(cart.getItem().getPrice());
            cartForm.setPrice(cart.getItem().getPrice() * cartForm.getStock());

            carts.add(cartForm);
        }

        return carts;
    }
}
