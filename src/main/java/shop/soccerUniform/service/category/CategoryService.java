package shop.soccerUniform.service.category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.soccerUniform.entity.Category;
import shop.soccerUniform.entity.dto.CategoryForm;
import shop.soccerUniform.entity.dto.CategorySearchForm;
import shop.soccerUniform.entity.enumtype.CategoryState;

import java.util.List;

public interface CategoryService {
    public void saveCategory(CategoryForm categoryForm);
    public void editCategory(Long categoryId, CategoryForm categoryForm);
    public CategoryForm detailCategory(Long categoryId);
    public Page<CategoryForm> categories(CategorySearchForm categorySearchForm, Pageable pageable);
    public void deleteCategory(Long categoryId);
    public List<Category> findByDepths(Integer depth);
    public List<Category> findChildren(Long categoryId);
}
