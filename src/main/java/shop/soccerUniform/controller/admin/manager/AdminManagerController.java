package shop.soccerUniform.controller.admin.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shop.soccerUniform.repository.manager.ManagerRepository;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminManagerController {

    private final ManagerRepository managerRepository;

    @GetMapping("/admin/managers")
    public String managers(Model model) {
        return "admin/user/managers";
    }
}
