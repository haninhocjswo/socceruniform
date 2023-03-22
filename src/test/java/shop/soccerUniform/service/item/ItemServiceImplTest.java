package shop.soccerUniform.service.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import shop.soccerUniform.entity.*;
import shop.soccerUniform.repository.cart.CartRepository;
import shop.soccerUniform.repository.item.ItemRepository;
import shop.soccerUniform.repository.itemOption.ItemOptionRepository;
import shop.soccerUniform.repository.itemOptionStock.ItemOptionStockRepository;
import shop.soccerUniform.repository.itemOptionValue.ItemOptionValueRepository;
import shop.soccerUniform.repository.member.MemberRepository;


import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemServiceImplTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemOptionRepository itemOptionRepository;

    @Autowired
    private ItemOptionStockRepository itemOptionStockRepository;

    @Autowired
    private ItemOptionValueRepository itemOptionValueRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void editItem() {
        Item item = itemRepository.findById(2L).get();
        ItemOptionStock optionStock = itemOptionStockRepository.findById(25L).get();
        ItemOption firstOption = itemOptionRepository.findById(2L).get();
        ItemOption secondOption = itemOptionRepository.findById(3L).get();
        ItemOptionValue firstValue = itemOptionValueRepository.findById(optionStock.getFirstOptionValue().getId()).get();
        ItemOptionValue secondValue = itemOptionValueRepository.findById(optionStock.getSecondOptionValue().getId()).get();
        Member member = memberRepository.findById(2L).get();

        Cart cart = new Cart("adidas", firstOption, secondOption, firstValue, secondValue, item, optionStock, 1, member);
        cart.addDate(LocalDateTime.now(), LocalDateTime.now());
        cartRepository.save(cart);
        //optionStock.delItemOptionStock(); 변경은 에러 안남
        
        //삭제는 외래키 때문에 에러남
        assertThatThrownBy(() -> itemOptionStockRepository.delete(optionStock))
                .isInstanceOf(RuntimeException.class);
    }
}