package shop.soccerUniform.controller.admin.item;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import shop.soccerUniform.entity.Manager;
import shop.soccerUniform.entity.dto.ItemSearchForm;
import shop.soccerUniform.entity.enumtype.UserState;
import shop.soccerUniform.service.ItemService;
import shop.soccerUniform.service.ManagerService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminItemController {

    private final ItemService itemService;
    private final ManagerService managerService;

    @GetMapping("/admin/items")
    public String items(@ModelAttribute(value = "itemSearchForm") ItemSearchForm itemSearchForm, @PageableDefault(size = 10, page = 0) Pageable pageable,
                        Model model) {

        return "admin/item/items";
    }

    @GetMapping("/admin/item/{itemId}")
    public String item(@PathVariable(value = "itemId") Long itemId, Model model) {
        return "admin/item/itemForm";
    }
}
