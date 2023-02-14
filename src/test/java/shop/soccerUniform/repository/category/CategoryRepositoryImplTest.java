package shop.soccerUniform.repository.category;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import shop.soccerUniform.entity.Category;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class CategoryRepositoryImplTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    EntityManager em;

    @Test
    void save() {
        Category category = new Category("상의", 1, null);
        category.addDate(LocalDateTime.now(), LocalDateTime.now());
        categoryRepository.save(category);
    }
}