package shop.soccerUniform.controller.admin.user.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
import shop.soccerUniform.util.PageList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminMemberController {

    private final MemberService memberService;

    @GetMapping("categories")
    public String members(@ModelAttribute(name = "memberSearchForm") MemberSearchForm memberSearchForm, Model model,
                          @PageableDefault(size = 10, page = 0, sort = "memberId", direction = Sort.Direction.DESC) Pageable pageable) {
        memberSearchForm.setState(UserState.ABLE);
        Page<MembersDTO> memberList = memberService.members(memberSearchForm, pageable);
        int currentPage = memberList.getNumber();
        int totalPages = memberList.getTotalPages();

        Map<String, Object> pagination = new HashMap<>();
        List<Integer> pageList = PageList.getPageList(currentPage, totalPages);
        pagination.put("totalElements", memberList.getTotalElements());
        pagination.put("totalPages", memberList.getTotalPages());
        pagination.put("currentPage", currentPage);
        pagination.put("isFirst", memberList.isFirst());
        pagination.put("isLast", memberList.isLast());
        pagination.put("pageList", pageList);

        model.addAttribute("members", memberList.getContent());
        model.addAttribute("memberSearchForm", memberSearchForm);
        model.addAttribute("pagination", pagination);
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
