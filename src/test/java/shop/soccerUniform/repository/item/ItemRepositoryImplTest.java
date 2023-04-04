package shop.soccerUniform.repository.item;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import shop.soccerUniform.entity.Category;
import shop.soccerUniform.entity.Item;
import shop.soccerUniform.entity.Manager;
import shop.soccerUniform.entity.enumtype.ItemState;
import shop.soccerUniform.entity.enumtype.OptionType;
import shop.soccerUniform.repository.category.CategoryRepository;
import shop.soccerUniform.repository.manager.ManagerRepository;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Transactional
@SpringBootTest
class ItemRepositoryImplTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @BeforeEach
    void beforeEach() {
        Manager manager = managerRepository.findById(1L).get();
        Category category = categoryRepository.findById(3L).get();

        Item item = new Item("text", manager, category, "제조사", "원산지", "설명", OptionType.DOUBLE, 111, ItemState.SOLD);
        item.addDate(LocalDateTime.now(), LocalDateTime.now());
        itemRepository.save(item);
    }

    @Test
    void updateTest() {
        Manager manager = managerRepository.findById(1L).get();
        Category category = categoryRepository.findById(3L).get();
        Item item = new Item("text", manager, category, "제조사1", "원산지", "설명", OptionType.DOUBLE, 111, ItemState.SOLD);
        Item item1 = itemRepository.findByName("text").get();
        item1.editItem(item.getName(), item.getManager(), item.getCategory(), item.getManufacturer(),
                item.getOrigin(), item.getDescription(), item.getPrice(), ItemState.SOLD);

        log.info("flush before");
        em.flush();
        log.info("flush after");

        //assertThat(item).isEqualTo(item1);
    }

}