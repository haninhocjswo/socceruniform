package shop.soccerUniform.controller.front.login;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import shop.soccerUniform.entity.dto.LoginForm;

import javax.validation.Valid;

@Controller
public class FrontLoginController {
    @GetMapping("/loginForm")
    public String loginForm() {
        return "front/loginForm";
    }

}
