package shop.soccerUniform.controller.admin.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.soccerUniform.entity.Category;
import shop.soccerUniform.entity.Manager;
import shop.soccerUniform.entity.dto.ItemEditForm;
import shop.soccerUniform.entity.dto.ItemForm;
import shop.soccerUniform.entity.dto.ItemSaveForm;
import shop.soccerUniform.entity.dto.ItemSearchForm;
import shop.soccerUniform.entity.enumtype.OptionType;
import shop.soccerUniform.entity.enumtype.UserState;
import shop.soccerUniform.service.category.CategoryService;
import shop.soccerUniform.service.item.ItemService;
import shop.soccerUniform.service.user.manager.ManagerService;
import shop.soccerUniform.util.PageList;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

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

        List<Manager> managers = managerService.findManagersByState(UserState.ABLE);
        for (Manager manager : managers) {
            log.info("manager={}", manager);
        }

        model.addAttribute("items", items.getContent());
        model.addAttribute("pagination", pagination);
        model.addAttribute("managers", managers);
        return "admin/item/items";
    }

    @GetMapping("/admin/item/{itemId}")
    public String item(@PathVariable(value = "itemId") Long itemId, Model model) {
        List<Manager> managers = managerService.findManagersByState(UserState.ABLE);
        List<Category> categories = categoryService.findByDepths(3);
        ItemEditForm itemEditForm = itemService.detailItem(itemId);

        model.addAttribute("itemEditForm", itemEditForm);
        model.addAttribute("managers", managers);
        model.addAttribute("categories", categories);

        return "admin/item/itemForm";
    }

    @GetMapping("/admin/item/register")
    public String itemRegisterForm(@ModelAttribute(name = "itemSaveForm") ItemSaveForm itemSaveForm, Model model) {
        List<Manager> managers = managerService.findManagersByState(UserState.ABLE);
        List<Category> categories = categoryService.findByDepths(3);

        model.addAttribute("managers", managers);
        model.addAttribute("categories", categories);
        return "admin/item/itemRegister";
    }

    @PostMapping("/admin/item/register")
    public String itemRegister(@Valid @ModelAttribute(name = "itemSaveForm") ItemSaveForm itemSaveForm, BindingResult bindingResult, Model model) throws IllegalAccessException {
        if(!StringUtils.hasText(itemSaveForm.getFirstOptionName())) {
            bindingResult.reject("firstOptionName", "첫번째 옵션을 확인해주세요.");
        }

        int option1Length = itemSaveForm.getOption1Values().split(",").length;
        int option2Length = itemSaveForm.getOption2Values().split(",").length;
        if(option1Length <= 0 || !StringUtils.hasText(itemSaveForm.getOption1Values())) {
            bindingResult.reject("option1Values", "옵션1 옵션값들을 확인해주세요.");
        }
        if(itemSaveForm.getOptionType() == OptionType.DOUBLE) {
            if(!StringUtils.hasText(itemSaveForm.getSecondOptionName())) {
                bindingResult.reject("secondOptionName", "두번째 옵션을 확인해주세요.");
            }
            if(option2Length <= 0 || !StringUtils.hasText(itemSaveForm.getOption2Values())) {
                bindingResult.reject("option2Values", "옵션2 옵션값들을 확인해주세요.");
            }
            if(option1Length*option2Length != itemSaveForm.getItemStocks().size()) {
                bindingResult.reject("optionStock", "옵션값과 재고가 맞지 않습니다. 확인해주세요.");
            }
        } else {
            if(option1Length != itemSaveForm.getItemStocks().size()) {
                bindingResult.reject("optionStock", "옵션값과 재고가 맞지 않습니다. 확인해주세요.");
            }
        }

        if(bindingResult.hasErrors()) {
            List<Manager> managers = managerService.findManagersByState(UserState.ABLE);
            List<Category> categories = categoryService.findByDepths(3);

            model.addAttribute("managers", managers);
            model.addAttribute("categories", categories);
            return "admin/item/itemRegister";
        }

        try {
            itemService.saveItem(itemSaveForm);
        } catch (RuntimeException e) {
            bindingResult.reject("savedError", e.getMessage());
            return "admin/item/itemRegister";
        }

        return "redirect:/admin/items";
    }

    @PostMapping("/admin/item/edit")
    public String editItem(@Valid @ModelAttribute(name = "itemEditForm") ItemEditForm itemEditForm, BindingResult bindingResult, Model model) throws IllegalAccessException {

        return "redirect:/admin/items";
    }

    @PostMapping("/admin/item/delete/{itemId}")
    public ResponseEntity<Map<String, Object>> deletedItem(@PathVariable(value = "itemId") Long itemId) {
        itemService.deletedItem(itemId);

        Map<String, Object> ajaxMap = new HashMap<>();
        ajaxMap.put("result", true);

        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(ajaxMap, HttpStatus.OK);

        return responseEntity;
    }
}
