package shop.soccerUniform.controller.front.members;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shop.soccerUniform.entity.dto.MemberForm;
import shop.soccerUniform.service.MemberService;

@Controller
@RequiredArgsConstructor
public class FrontMemberController {

    private final MemberService memberService;

    @GetMapping("/joinForm")
    public String joinForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "front/joinForm";
    }
}
