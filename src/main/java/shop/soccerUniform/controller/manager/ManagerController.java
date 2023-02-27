package shop.soccerUniform.controller.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shop.soccerUniform.entity.dto.ManagerDTO;
import shop.soccerUniform.service.ManagerService;

@Controller
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @GetMapping("/manager/main")
    public String main() {
        return "manager/managerMain";
    }

    @GetMapping("/manager/login")
    public String managerLogin(Model model) {
        model.addAttribute("managerForm", new ManagerDTO());
        return "manager/managerLoginForm";
    }

    @GetMapping("/manager/register")
    public String managerRegister(Model model) {
        model.addAttribute("managerForm", new ManagerDTO());
        return "manager/managerRegister";
    }
}
