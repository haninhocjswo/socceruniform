package shop.soccerUniform.controller.admin.category;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shop.soccerUniform.entity.Category;
import shop.soccerUniform.entity.dto.CategoryForm;
import shop.soccerUniform.entity.dto.CategorySearchForm;
import shop.soccerUniform.entity.enumtype.CategoryState;
import shop.soccerUniform.service.CategoryService;
import shop.soccerUniform.util.PageList;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminCategoryController {

    private final CategoryService categoryService;

    @GetMapping("/admin/categories")
    public String categories(@ModelAttribute(value = "categorySearchForm") CategorySearchForm categorySearchForm, Model model, @PageableDefault(size = 10, page = 0) Pageable pageable) {
        Page<CategoryForm> categories = categoryService.categories(categorySearchForm, pageable);
        int currentPage = categories.getNumber();
        int totalPages = categories.getTotalPages();

        Map<String, Object> pagination = new HashMap<>();
        List<Integer> pageList = PageList.getPageList(currentPage, totalPages);
        pagination.put("totalElements", categories.getTotalElements());
        pagination.put("totalPages", totalPages);
        pagination.put("currentPage", currentPage);
        pagination.put("isFirst", categories.isFirst());
        pagination.put("isLast", categories.isLast());
        pagination.put("pageList", pageList);

        log.info("pagination={}", pagination.entrySet());

        model.addAttribute("categories", categories.getContent());
        model.addAttribute("pagination", pagination);
        return "admin/category/categories";
    }

    @GetMapping("/admin/category/{categoryId}")
    public String detailCategory(@PathVariable(value = "categoryId") Long categoryId, Model model) {
        CategoryForm categoryForm = categoryService.detailCategory(categoryId);
        List<Category> parents = new ArrayList<>();
        if(categoryForm.getDepth() != null) {
            parents = categoryService.findByDepths(categoryForm.getDepth());
        }

        model.addAttribute("categoryForm", categoryForm);
        model.addAttribute("parents", parents);
        return "admin/category/categoryForm";
    }

    @PostMapping("/admin/category/edit/{categoryId}")
    public String editCategory(@Valid @ModelAttribute(value = "categoryForm") CategoryForm categoryForm, BindingResult bindingResult,
                               @PathVariable(value = "categoryId") Long categoryId) {
        if(bindingResult.hasErrors()) {
            return "admin/category/categoryForm";
        }

        categoryService.editCategory(categoryId, categoryForm);
        return "redirect:/admin/categories";
    }

    @PostMapping("/admin/category/delete/{categoryId}")
    public ResponseEntity<Map<String, Object>> deleteCategory(@PathVariable(value = "categoryId") Long categoryId) {
        categoryService.deleteCategory(categoryId);
        Map<String, Object> ajaxMap = new HashMap<>();
        ajaxMap.put("result", true);

        return new ResponseEntity<>(ajaxMap, HttpStatus.OK);
    }

    @GetMapping("/admin/category/register")
    public String registerForm(Model model) {
        model.addAttribute("categoryForm", new CategoryForm());
        return "admin/category/categoryRegister";
    }

    @PostMapping("/admin/category/register")
    public String registerCategory(@Valid @ModelAttribute(value = "categoryForm") CategoryForm categoryForm, BindingResult bindingResult) {
        log.info("parentId={}", categoryForm.getParentId());

        //필드 에러
        if(bindingResult.hasErrors()) {
            log.info("bindingResult={}", bindingResult);
            return "admin/category/categoryRegister";
        }

        categoryService.saveCategory(categoryForm);

        return "redirect:/admin/categories";
    }

    @PostMapping("/admin/category/parent_ajax")
    public ResponseEntity<Map<String, Object>> parent_ajax(@RequestParam(value = "childDepth") Integer childDepth) {
        Map<String, Object> ajaxMap = new HashMap<>();
        List<Category> parents = new ArrayList<>();
        if(childDepth > 1) {
            int parentDepth = childDepth - 1;
            parents = categoryService.findByDepths(parentDepth);
        }

        if(parents.size() > 0) {
            ajaxMap.put("result", true);
            ajaxMap.put("parents", parents);
        } else {
            ajaxMap.put("result", false);
            ajaxMap.put("message", "상위 카테고리가 존재하지 않습니다.");
        }
        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(ajaxMap, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping("/admin/category/findByChildDepths_ajax")
    public ResponseEntity<Map<String, Object>> findByChildDepthsAjax(@RequestParam Long categoryId) {
        Map<String, Object> ajaxMap = new HashMap<>();
        List<Category> children = categoryService.findChildren(categoryId);

        if(children.size() > 0) {
            ajaxMap.put("result", true);
            ajaxMap.put("children", children);
        } else {
            ajaxMap.put("result", false);
            ajaxMap.put("message", "하위 카테고리가 존재하지 않습니다.");
        }

        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(ajaxMap, HttpStatus.OK);
        return responseEntity;
    }
}
