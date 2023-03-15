package shop.soccerUniform.controller.front.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import shop.soccerUniform.entity.dto.MemberForm;
import shop.soccerUniform.entity.dto.MemberSaveForm;
import shop.soccerUniform.service.user.member.MemberService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FrontMemberController {

    private final MemberService memberService;

    @GetMapping("/user/register")
    public String registerForm(@ModelAttribute(name = "memberSaveForm") MemberSaveForm memberSaveForm, Model model) {
        return "front/member/joinForm";
    }

    @PostMapping("/user/register")
    public String registerMember(@Valid @ModelAttribute(name = "memberSaveForm") MemberSaveForm memberSaveForm, BindingResult bindingResult, Model model) {
        if(!memberSaveForm.isDuplicatedLoginId()) {
            bindingResult.reject("loginId", "아이디 중복체크가 필요합니다.");
        }

        if(StringUtils.hasText(memberSaveForm.getLoginId())) {
            boolean duplicatedFlag = memberService.duplicateLoginId(memberSaveForm.getLoginId());
            if(!duplicatedFlag) {
                bindingResult.reject("loginId", "아이디 중복체크가 필요합니다.");
            }
        }

        if(bindingResult.hasErrors()) {
            log.info("bindingResult={}", bindingResult);
            return "front/member/joinForm";
        }

        memberService.saveMember(memberSaveForm);
        return "redirect:/home";
    }

    @PostMapping("/user/duplicateLoginId")
    public ResponseEntity<Map<String, Object>> duplicateLoginId(@RequestParam(name = "checkLoginId") String checkLoginId) {
        Map<String, Object> ajaxMap = new HashMap<>();
        boolean result = memberService.duplicateLoginId(checkLoginId);
        ajaxMap.put("result", result);
        if(result) {
            ajaxMap.put("message", "사용가능한 아이디입니다.");
        } else {
            ajaxMap.put("message", "이미 존재하는 아이디입니다.");
        }

        ResponseEntity<Map<String, Object>> responseEntity = new ResponseEntity<>(ajaxMap, HttpStatus.OK);
        return responseEntity;
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
