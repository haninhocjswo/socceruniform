package shop.soccerUniform.controller.front.members;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import shop.soccerUniform.entity.dto.MemberForm;
import shop.soccerUniform.service.MemberService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class FrontMemberController {

    private final MemberService memberService;

    @GetMapping("/joinForm")
    public String joinForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "front/joinForm";
    }

    @PostMapping("/joinMember")
    public String joinMember(@ModelAttribute(name = "memberForm") MemberForm memberForm) {
        memberService.saveMember(memberForm);
        return "redirect:/home";
    }
}
