package shop.soccerUniform.controller.front.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.soccerUniform.entity.dto.MemberForm;
import shop.soccerUniform.service.MemberService;

@Controller
@RequiredArgsConstructor
public class FrontMemberController {

    private final MemberService memberService;

    @GetMapping("/joinForm")
    public String joinForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "front/member/joinForm";
    }

    @PostMapping("/joinMember")
    public String joinMember(@ModelAttribute(name = "memberForm") MemberForm memberForm) {
        memberService.saveMember(memberForm);
        return "redirect:/home";
    }

    @GetMapping("/member/myPage/{memberId}")
    public String myPage(@PathVariable(name = "memberId") Long memberId, Model model) {
        MemberForm member = memberService.findMember(memberId);
        model.addAttribute("memberForm", member);
        return "front/member/myPage";
    }

    @GetMapping("/member/deletedMember/{memberId}")
    public String deletedMember(@PathVariable(name = "memberId") Long memberId) {
        return "front/member/deletedMember";
    }

    @PostMapping("/member/edit")
    public String editMember(@ModelAttribute(name = "memberForm") MemberForm memberForm) {
        memberService.updateMember(memberForm);
        return "redirect:/";
    }
}
