package shop.soccerUniform.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.soccerUniform.entity.Category;
import shop.soccerUniform.entity.dto.CategoryForm;
import shop.soccerUniform.entity.dto.CategorySearchForm;
import shop.soccerUniform.entity.enumtype.CategoryState;
import shop.soccerUniform.repository.category.CategoryRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Transactional
    @Override
    public void saveCategory(CategoryForm categoryForm) {
        Category parent = null;
        if(categoryForm.getParentId() > 0) {
            parent = categoryRepository.findById(categoryForm.getParentId()).get();
        }

        Category category = new Category(categoryForm.getName(), categoryForm.getDepth(), parent, CategoryState.ABLE);
        category.addDate(LocalDateTime.now(), LocalDateTime.now());
        categoryRepository.save(category);
    }

    @Transactional
    @Override
    public void editCategory(Long categoryId, CategoryForm categoryForm) {
        System.out.println("CategoryServiceImpl.editCategory");
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> {
                    throw new RuntimeException("해당 카테고리는 존재하지 않습니다.");
                });

        Category parent = categoryRepository.findById(categoryForm.getParentId()).get();

        category.editCategory(categoryForm.getName(), parent, categoryForm.getState());
        category.editDate(LocalDateTime.now());
    }

    @Override
    public CategoryForm detailCategory(Long categoryId) {
        System.out.println("CategoryServiceImpl.detailCategory");
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if(category == null) {
            throw new RuntimeException("해당 카테고리는 존재하지 않습니다.");
        }

        CategoryForm categoryForm = new CategoryForm();
        categoryForm.setCategoryId(categoryId);
        categoryForm.setName(category.getName());
        categoryForm.setDepth(category.getDepth());
        categoryForm.setState(category.getState());
        if(category.getParent() != null) {
            categoryForm.setParentId(category.getParent().getId());
        }

        return categoryForm;
    }

    @Override
    public Page<CategoryForm> categories(CategorySearchForm categorySearchForm, Pageable pageable) {
        return categoryRepository.categories(categorySearchForm, pageable);
    }

    @Transactional
    @Override
    public void deleteCategory(Long categoryId) {
        System.out.println("CategoryServiceImpl.deleteCategory");
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if(category == null) {
            throw new RuntimeException("해당 카테고리는 존재하지 않습니다.");
        }

        category.delCategory();
        category.editDate(LocalDateTime.now());
    }

    @Override
    public List<Category> findParents(Integer parentDepth) {
        return categoryRepository.findByParentDepths(parentDepth);
    }

    @Override
    public List<Category> findChildren(Long categoryId) {
        return categoryRepository.findByChildDepths(categoryId);
    }

    @Override
    public List<Category> findCategoriesByState(CategoryState categoryState) {
        return categoryRepository.findByCategoriesByState(categoryState);
    }
}
