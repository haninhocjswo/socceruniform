package shop.soccerUniform.controller.admin.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.soccerUniform.entity.Category;
import shop.soccerUniform.entity.Manager;
import shop.soccerUniform.entity.dto.ItemForm;
import shop.soccerUniform.entity.dto.ItemSearchForm;
import shop.soccerUniform.entity.enumtype.UserState;
import shop.soccerUniform.service.category.CategoryService;
import shop.soccerUniform.service.item.ItemService;
import shop.soccerUniform.service.manager.ManagerService;
import shop.soccerUniform.util.PageList;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminItemController {

    private final ItemService itemService;
    private final ManagerService managerService;

    private final CategoryService categoryService;

    @GetMapping("/admin/items")
    public String items(@ModelAttribute(value = "itemSearchForm") ItemSearchForm itemSearchForm, @PageableDefault(size = 10, page = 0) Pageable pageable,
                        Model model) {
        Page<ItemForm> items = itemService.items(itemSearchForm, pageable);
        int currentPage = items.getNumber();
        int totalPages = items.getTotalPages();

        Map<String, Object> pagination = new HashMap<>();
        List<Integer> pageList = PageList.getPageList(currentPage, totalPages);
        pagination.put("totalElements", items.getTotalElements());
        pagination.put("totalPages", totalPages);
        pagination.put("currentPage", currentPage);
        pagination.put("isFirst", items.isFirst());
        pagination.put("isLast", items.isLast());
        pagination.put("pageList", pageList);

        model.addAttribute("items", items.getContent());
        model.addAttribute("pagination", pagination);
        return "admin/item/items";
    }

    @GetMapping("/admin/item/{itemId}")
    public String item(@PathVariable(value = "itemId") Long itemId, Model model) {

        return "admin/item/itemForm";
    }

    @GetMapping("/admin/item/register")
    public String itemRegisterForm(@ModelAttribute(name = "itemForm") ItemForm itemForm, Model model) {
        List<Manager> managers = managerService.findManagersByState(UserState.ABLE);
        List<Category> categories = categoryService.findByDepths(3);

        model.addAttribute("managers", managers);
        model.addAttribute("categories", categories);
        return "admin/item/itemRegister";
    }

    @PostMapping("/admin/item/register")
    public String itemRegister(@Valid @ModelAttribute ItemForm itemForm, BindingResult bindingResult, Model model) throws IllegalAccessException {

        //필드에러
        if(bindingResult.hasErrors()) {
            log.info("bindingResult={}", bindingResult);
            List<Manager> managers = managerService.findManagersByState(UserState.ABLE);
            List<Category> categories = categoryService.findByDepths(3);

            model.addAttribute("managers", managers);
            model.addAttribute("categories", categories);
            return "admin/item/itemRegister";
        }

        itemService.saveItem(itemForm);

        return "redirect:/admin/items";
    }
}
