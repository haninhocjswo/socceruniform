package shop.soccerUniform.repository.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.soccerUniform.entity.Category;
import shop.soccerUniform.entity.dto.CategoryForm;
import shop.soccerUniform.entity.dto.CategorySearchForm;

import java.util.List;

public interface CategoryQueryRepository {

    public Page<CategoryForm> categories(CategorySearchForm categorySearchForm, Pageable pageable);

    public List<Category> findByParentDepths(Integer parentDepth);

    public Category findByParentDepth(Long parentId);
}
