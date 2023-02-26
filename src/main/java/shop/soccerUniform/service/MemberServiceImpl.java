package shop.soccerUniform.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.soccerUniform.entity.Member;
import shop.soccerUniform.entity.Point;
import shop.soccerUniform.entity.dto.MemberForm;
import shop.soccerUniform.entity.dto.MemberSearchForm;
import shop.soccerUniform.entity.dto.MembersDTO;
import shop.soccerUniform.entity.enumtype.Grade;
import shop.soccerUniform.entity.enumtype.PointState;
import shop.soccerUniform.entity.enumtype.Role;
import shop.soccerUniform.entity.enumtype.UserState;
import shop.soccerUniform.repository.member.MemberRepository;
import shop.soccerUniform.repository.point.PointRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PointRepository pointRepository;

    @Transactional
    @Override
    public void saveMember(MemberForm memberForm) {
        Member member = new Member(memberForm.getGender(), Grade.BRONZE, memberForm.getMobile(), memberForm.getHomeNum());
        member.addUser(memberForm.getLoginId(), memberForm.getPassword(), memberForm.getUsername(), memberForm.getEmail(), Role.ROLE_MEMBER, UserState.ABLE);
        member.addDate(LocalDateTime.now(), LocalDateTime.now());
        memberRepository.save(member);

        Point point = new Point(member, 0, 0, PointState.ABLE, 1);
        point.addDate(LocalDateTime.now(), LocalDateTime.now());
        pointRepository.save(point);
    }

    @Transactional
    @Override
    public void updateMember(MemberForm memberForm) {
        Optional<Member> findMember = memberRepository.findById(memberForm.getMemberId());
        Member member = findMember.orElse(null);
        if(member != null) {
            member.editMember(memberForm.getGender(), member.getGrade(), member.getMobile(), member.getHomeNum());
            member.editUser(memberForm.getPassword(), memberForm.getUsername(), memberForm.getEmail(), memberForm.getState());
            member.editDate(LocalDateTime.now());
        }
    }

    @Transactional
    @Override
    public void deletedMember(Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member = findMember.orElse(null);
        log.info("전={}", member.getState());
        if(member != null) {
            member.delUser();
            log.info("후={}", member.getState());
        }
    }

    @Override
    public Page<MembersDTO> members(MemberSearchForm memberSearchForm, Pageable pageable) {
        Page<MembersDTO> members = memberRepository.members(memberSearchForm, pageable);
        return members;
    }

    @Override
    public MemberForm findMember(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);

        MemberForm memberForm = new MemberForm();
        if(!member.isEmpty()) {
            memberForm.setMemberId(member.get().getId());
            memberForm.setLoginId(member.get().getLoginId());
            memberForm.setPassword(member.get().getPassword());
            memberForm.setUsername(member.get().getUsername());
            memberForm.setEmail(member.get().getEmail());
            memberForm.setGrade(member.get().getGrade());
            memberForm.setGender(member.get().getGender());
            memberForm.setState(member.get().getState());
            memberForm.setMobile(member.get().getMobile());
            memberForm.setHomeNum(member.get().getHomeNum());
            Point point = pointRepository.findByIdAndState(member.get().getId(), PointState.ABLE);
            memberForm.setPoint(point.getPoint());
        }

        return memberForm;
    }

}

