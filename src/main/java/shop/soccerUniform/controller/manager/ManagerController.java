package shop.soccerUniform.controller.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import shop.soccerUniform.entity.dto.ManagerDTO;
import shop.soccerUniform.service.user.manager.ManagerService;

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
        return "manager/managerLoginForm";
    }

    @GetMapping("/manager/register")
    public String managerRegisterForm(Model model) {
        model.addAttribute("managerForm", new ManagerDTO());
        return "manager/managerRegister";
    }

    @PostMapping("/manager/register")
    public String managerRegister(@ModelAttribute(value = "managerForm") ManagerDTO managerDTO) {
        managerService.save(managerDTO);
        return "redirect:/manager/login";
    }
}
