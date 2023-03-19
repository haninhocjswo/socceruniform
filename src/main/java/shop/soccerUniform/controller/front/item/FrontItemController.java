package shop.soccerUniform.controller.front.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import shop.soccerUniform.controller.config.login.PrincipalDetail;
import shop.soccerUniform.entity.ItemOption;
import shop.soccerUniform.entity.User;
import shop.soccerUniform.entity.dto.FrontItemForm;
import shop.soccerUniform.service.item.ItemService;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Iterator;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FrontItemController {

    private final ItemService itemService;

    @GetMapping("/item/{itemId}")
    public String itemPage(@PathVariable(name = "itemId") Long itemId, Model model, @AuthenticationPrincipal PrincipalDetail loginUser) {
        FrontItemForm frontItemForm = itemService.showItem(itemId);
        if(loginUser != null) {
            model.addAttribute("loginUser", loginUser.getUser());
        }
        model.addAttribute("item", frontItemForm);

        return "front/item/item";
    }
}
