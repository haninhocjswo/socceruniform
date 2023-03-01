package shop.soccerUniform.repository.category;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import shop.soccerUniform.entity.Category;
import shop.soccerUniform.entity.dto.CategoryForm;
import shop.soccerUniform.entity.dto.CategorySearchForm;
import shop.soccerUniform.entity.enumtype.CategoryState;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@Transactional
@Commit
class CategoryRepositoryImplTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    EntityManager em;

    @BeforeEach
    void before() {
        Category depth1 = new Category("최상위", 1, null, CategoryState.ABLE);
        depth1.addDate(LocalDateTime.now(), LocalDateTime.now());
        Category depth2 = new Category("상위", 2, depth1, CategoryState.ABLE);
        depth2.addDate(LocalDateTime.now(), LocalDateTime.now());
        categoryRepository.save(depth1);
        categoryRepository.save(depth2);
    }

    @Test
    void save() {
        Category category = new Category("상의", 1, null, null);
        category.addDate(LocalDateTime.now(), LocalDateTime.now());
        em.persist(category);
        log.info("=================================");
        em.flush();
    }

    @Test
    void categories() {
        Pageable pageable = PageRequest.of(0, 5);
        CategorySearchForm searchForm = new CategorySearchForm();

        Page<CategoryForm> categories = categoryRepository.categories(searchForm, pageable);

        List<CategoryForm> content = categories.getContent();

        for (CategoryForm categoryForm : content) {
            log.info("카테고리={}", categoryForm);
        }
    }
}