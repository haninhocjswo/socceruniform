package shop.soccerUniform.service.cart;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import shop.soccerUniform.entity.*;
import shop.soccerUniform.entity.dto.CartForm;
import shop.soccerUniform.repository.cart.CartRepository;
import shop.soccerUniform.repository.item.ItemRepository;
import shop.soccerUniform.repository.itemOptionStock.ItemOptionStockRepository;
import shop.soccerUniform.repository.itemOptionValue.ItemOptionValueRepository;
import shop.soccerUniform.repository.member.MemberRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final ItemOptionValueRepository itemOptionValueRepository;
    private final ItemOptionStockRepository itemOptionStockRepository;

    @Transactional
    @Override
    public boolean saveCart(CartForm cartForm) {
        if(cartForm.getSelectedItems().size() == 0) return false;

        for(String selectedItem : cartForm.getSelectedItems()) {
            Optional<Item> itemOptional = itemRepository.findById(cartForm.getItemId());
            if(itemOptional.isEmpty()) return false;
            //Item 뽑기
            Item item = itemOptional.get();
            ItemOption firstOption = null;
            ItemOption secondOption = null;

            //ItemOption 뽑기
            for(ItemOption itemOption : item.getItemOptions()) {
                if(itemOption.getOptionSort() == 1) firstOption = itemOption;
                if(itemOption.getOptionSort() == 2) secondOption = itemOption;
            }

            //ItemOptionValue 뽑기
            String[] splitSelectedItemInfo = selectedItem.split("_");
            if(splitSelectedItemInfo.length != 3) return false;

            Optional<ItemOptionValue> firstOptionValueOptional = itemOptionValueRepository.findById(Long.parseLong(splitSelectedItemInfo[0]));
            if(firstOptionValueOptional.isEmpty()) return false;
            ItemOptionValue firstOptionValue = firstOptionValueOptional.get();

            Optional<ItemOptionValue> secondOptionValueOptional = itemOptionValueRepository.findById(Long.parseLong(splitSelectedItemInfo[1]));
            ItemOptionValue secondOptionValue = null;
            if(secondOptionValueOptional.isEmpty()) {
                if(!splitSelectedItemInfo[1].equals("0")) return false;
            } else {
                secondOptionValue = secondOptionValueOptional.get();
            }

            //ItemOptionStock 뽑기
            Optional<ItemOptionStock> itemOptionStockOptional =
                    itemOptionStockRepository.findByFirstOptionValueAndSecondOptionValue(firstOptionValue, secondOptionValue);
            if(itemOptionStockOptional.isEmpty()) return false;
            ItemOptionStock itemOptionStock = itemOptionStockOptional.get();

            //로그인한 유저
            Optional<Member> memberOptional = memberRepository.findById(cartForm.getMemberId());
            if(memberOptional.isEmpty()) return false;
            Member member = memberOptional.get();

            Cart cart = new Cart(item.getName(), firstOption, secondOption, firstOptionValue,
                    secondOptionValue, item, itemOptionStock, Integer.parseInt(splitSelectedItemInfo[2]), member);
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
    public List<CartForm> findCartsByLoginId(String loginId) {
        Optional<Member> memberOptional = memberRepository.findByLoginId(loginId);
        List<CartForm> carts = new ArrayList<>();
        if(memberOptional.isEmpty()) {
            return carts;
        }

        Member member = memberOptional.get();
        List<Cart> findCarts = cartRepository.findByMemberId(member.getId());
        if(findCarts.size() == 0) {
            return carts;
        }

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
            cartForm.setPrice(cart.getItem().getPrice() * cartForm.getStock());

            carts.add(cartForm);
        }

        return carts;
    }
}
