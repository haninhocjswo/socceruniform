package shop.soccerUniform.controller.admin.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import shop.soccerUniform.entity.dto.MemberForm;
import shop.soccerUniform.entity.dto.MemberSearchForm;
import shop.soccerUniform.entity.dto.MembersDTO;
import shop.soccerUniform.entity.enumtype.UserState;
import shop.soccerUniform.service.MemberService;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminMemberController {

    private final MemberService memberService;

    @GetMapping("/admin/members")
    public String members(@ModelAttribute(name = "memberSearchForm") MemberSearchForm memberSearchForm, Model model) {
        memberSearchForm.setState(UserState.ABLE);
        List<MembersDTO> members = memberService.members(memberSearchForm);
        model.addAttribute("members", members);
        model.addAttribute("memberSearchForm", memberSearchForm);
        return "admin/user/members";
    }

    @GetMapping("/admin/member/{memberId}")
    public String memberForm(@PathVariable(name = "memberId") Long memberId, Model model) {
        MemberForm member = memberService.findMember(memberId);
        model.addAttribute("memberForm", member);
        return "admin/user/memberForm";
    }

    @PostMapping("/admin/member/edit/{memberId}")
    public String editMember(@PathVariable(name = "memberId") Long memberId, @ModelAttribute(name = "memberForm") MemberForm memberForm) {
        memberService.updateMember(memberForm);
        return "redirect:/admin/user/members";
    }

    @PostMapping("/admin/member/delete/{memberId}")
    public ResponseEntity<HashMap<String, Object>> deletedMember(@PathVariable(name = "memberId") Long memberId) {
        HashMap<String, Object> ajaxMap = new HashMap<>();
        memberService.deletedMember(memberId);
        ajaxMap.put("result", true);
        ResponseEntity<HashMap<String, Object>> responseEntity = new ResponseEntity<>(ajaxMap, HttpStatus.OK);
        return responseEntity;
    }
}
