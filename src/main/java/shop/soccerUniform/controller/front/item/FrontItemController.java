package shop.soccerUniform.controller.front.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.soccerUniform.entity.dto.FrontItemForm;
import shop.soccerUniform.entity.dto.ItemEditForm;
import shop.soccerUniform.service.item.ItemService;

@Controller
@RequiredArgsConstructor
public class FrontItemController {

    private final ItemService itemService;

    @GetMapping("/item/{itemId}")
    public String itemPage(@PathVariable(name = "itemId") Long itemId, Model model) {
        FrontItemForm frontItemForm = itemService.showItem(itemId);
        model.addAttribute("item", frontItemForm);
        return "front/item/item";
    }
}
