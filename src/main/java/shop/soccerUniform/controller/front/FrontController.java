package shop.soccerUniform.controller.front;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shop.soccerUniform.entity.dto.ItemSearchForm;
import shop.soccerUniform.service.item.ItemService;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FrontController {

    private ItemService itemService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String home(Model model, Pageable pageable) {
        ItemSearchForm itemSearchForm = new ItemSearchForm();
        // TODO 아이템리스트 40개만 가져오기 그리고 무한 스크롤 구현
        itemService.items(itemSearchForm, pageable);
        return "front/home";
    }
}
