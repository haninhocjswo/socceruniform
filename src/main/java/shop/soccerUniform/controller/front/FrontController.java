package shop.soccerUniform.controller.front;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shop.soccerUniform.entity.dto.ItemForm;
import shop.soccerUniform.entity.dto.ItemSearchForm;
import shop.soccerUniform.entity.enumtype.ItemState;
import shop.soccerUniform.service.item.ItemService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FrontController {

    private final ItemService itemService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model, @PageableDefault(size = 40) Pageable pageable) {
        ItemSearchForm itemSearchForm = new ItemSearchForm();
        itemSearchForm.setState(ItemState.SOLD);

        Page<ItemForm> itemList = itemService.items(itemSearchForm, pageable);
        List<ItemForm> content = itemList.getContent();
        log.info("content size={}", content.size());
        model.addAttribute("items", content);
        return "front/home";
    }
}
