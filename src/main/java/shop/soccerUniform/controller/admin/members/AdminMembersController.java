package shop.soccerUniform.controller.admin.members;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.soccerUniform.entity.dto.MemberForm;
import shop.soccerUniform.entity.dto.MemberSearchForm;
import shop.soccerUniform.entity.dto.MembersDTO;
import shop.soccerUniform.service.MemberService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AdminMembersController {

    private final MemberService memberService;

    @GetMapping("/admin/members")
    public String members(@ModelAttribute(name = "memberSearchForm") MemberSearchForm memberSearchForm, Model model) {
        List<MembersDTO> members = memberService.members(memberSearchForm);
        model.addAttribute("members", members);
        model.addAttribute("memberSearchForm", memberSearchForm);
        return "admin/members/members";
    }

    @GetMapping("/admin/member/{memberId}")
    public String memberForm(@PathVariable(name = "memberId") Long memberId, Model model) {
        return "admin/memberForm";
    }

    @PostMapping("/admin/member/{memberId}")
    public String updateMember(@PathVariable(name = "memberId") Long memberId, @ModelAttribute(name = "memberForm") MemberForm memberForm) {
        return "redirect:/admin/members";
    }
}
