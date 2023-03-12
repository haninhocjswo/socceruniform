package shop.soccerUniform.controller.admin.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import shop.soccerUniform.entity.dto.ItemForm;
import shop.soccerUniform.entity.dto.ItemSaveForm;
import shop.soccerUniform.entity.dto.ItemSearchForm;
import shop.soccerUniform.entity.enumtype.OptionType;
import shop.soccerUniform.entity.enumtype.UserState;
import shop.soccerUniform.service.category.CategoryService;
import shop.soccerUniform.service.item.ItemService;
import shop.soccerUniform.service.manager.ManagerService;
import shop.soccerUniform.util.PageList;

import javax.validation.Valid;
import java.lang.reflect.Field;
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
            bindingResult.reject("firstOptionName", "옵션1명은 필수값입니다.");
        }

        Map<String, Object> itemSaveFormMap = new HashMap<>();
        Field[] fields = itemSaveForm.getClass().getDeclaredFields();
        String firstValueName = "";
        String secondValueName = "";

        for (Field field : fields) {
            if(field.get(itemSaveForm) != null) {
                itemSaveFormMap.put(field.getName(), field.get(itemSaveForm));
            }
        }

        if(itemSaveForm.getOptionType() == OptionType.SINGLE) {
            String singleStockName = "";
            for(int i = 0; i < itemSaveForm.getItemOption1ValueSize(); i++) {
                firstValueName = "valueName1_" + (i+1);
                singleStockName = "stock_" + (i+1) + "_0";

                if(itemSaveFormMap.get(firstValueName) == null) {
                    bindingResult.reject(firstValueName, "옵션값을 확인해주세요.");
                }

                if(itemSaveFormMap.get(singleStockName) == null) {
                    bindingResult.reject(singleStockName, "재고를 확인해주세요.");
                }
            }
        }

        firstValueName = "";
        if(itemSaveForm.getOptionType() == OptionType.DOUBLE) {
            String doubleStockName = "";
            for(int i = 0; i < itemSaveForm.getItemOption1ValueSize(); i++) {
                for(int k = 0; k < itemSaveForm.getItemOption2ValueSize(); k++) {
                    firstValueName = "valueName1_" + (i+1);
                    secondValueName = "valueName2_" + (k+1);
                    doubleStockName = "stock_" + (i+1) + "_" + (k+1);

                    if(itemSaveFormMap.get(firstValueName) == null) {
                        bindingResult.reject(firstValueName, "옵션값을 확인해주세요.");
                    }

                    if(itemSaveFormMap.get(secondValueName) == null) {
                        bindingResult.reject(secondValueName, "옵션값을 확인해주세요.");
                    }

                    if(itemSaveFormMap.get(doubleStockName) == null) {
                        bindingResult.reject(doubleStockName, "재고를 확인해주세요.");
                    }
                }
            }
        }

        //필드에러
        if(bindingResult.hasErrors()) {
            log.info("bindingResult={}", bindingResult);
            List<Manager> managers = managerService.findManagersByState(UserState.ABLE);
            List<Category> categories = categoryService.findByDepths(3);

            model.addAttribute("managers", managers);
            model.addAttribute("categories", categories);
            return "admin/item/itemRegister";
        }

        itemSaveForm.setValueName1_1(itemSaveForm.getValueName1_1().replaceAll(",", ""));
        itemService.saveItem(itemSaveForm);

        return "redirect:/admin/items";
    }
}
