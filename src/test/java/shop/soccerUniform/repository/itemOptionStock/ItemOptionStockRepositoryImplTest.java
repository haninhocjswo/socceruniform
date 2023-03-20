package shop.soccerUniform.repository.itemOptionStock;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shop.soccerUniform.entity.ItemOptionStock;
import shop.soccerUniform.entity.ItemOptionValue;
import shop.soccerUniform.repository.itemOptionValue.ItemOptionValueRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemOptionStockRepositoryImplTest {

    @Autowired
    private ItemOptionStockRepository itemOptionStockRepository;

    @Autowired
    private ItemOptionValueRepository itemOptionValueRepository;

    @Test
    void findByFirstOptionValueAndSecondOptionValueTest() {
        Long firstOptionValueId = 6L;
        Long secondOptionValueId = 13L;
//        Optional<ItemOptionStock> itemOptionStockOptional =
//                itemOptionStockRepository.findByFirstOptionValueAndSecondOptionValue(firstOptionValueId, secondOptionValueId);
//        ItemOptionStock itemOptionStock = itemOptionStockOptional.get();
//        Assertions.assertThat(itemOptionStock.getId()).isEqualTo(7);
    }
}