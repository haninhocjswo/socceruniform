package shop.soccerUniform.controller.admin.manager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import shop.soccerUniform.entity.dto.ManagerDTO;
import shop.soccerUniform.entity.dto.ManagerSearchForm;
import shop.soccerUniform.repository.manager.ManagerRepository;
import shop.soccerUniform.service.ManagerService;
import shop.soccerUniform.util.PageList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminManagerController {

    private final ManagerService managerService;

    @GetMapping("/admin/managers")
    public String managers(@ModelAttribute(value = "managerSearchForm") ManagerSearchForm managerSearchForm, Model model,
                           @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<ManagerDTO> managers = managerService.managers(managerSearchForm, pageable);

        Map<String, Object> pagination = new HashMap<>();
        List<Integer> pageList = PageList.getPageList(managers.getNumber(), managers.getTotalPages());
        pagination.put("totalElements", managers.getTotalElements());
        pagination.put("totalPages", managers.getTotalPages());
        pagination.put("currentPage", managers.getNumber());
        pagination.put("isFirst", managers.isFirst());
        pagination.put("isLast", managers.isLast());
        pagination.put("pageList", pageList);

        model.addAttribute("managers", managers.getContent());
        model.addAttribute("managerSearchForm", managerSearchForm);
        model.addAttribute("pagination", pagination);
        return "admin/user/managers";
    }
}
