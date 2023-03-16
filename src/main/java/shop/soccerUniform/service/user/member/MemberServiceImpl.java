package shop.soccerUniform.service.user.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.soccerUniform.entity.Address;
import shop.soccerUniform.entity.Member;
import shop.soccerUniform.entity.Point;
import shop.soccerUniform.entity.dto.MemberForm;
import shop.soccerUniform.entity.dto.MemberSaveForm;
import shop.soccerUniform.entity.dto.MemberSearchForm;
import shop.soccerUniform.entity.dto.MembersDTO;
import shop.soccerUniform.entity.enumtype.*;
import shop.soccerUniform.repository.address.AddressRepository;
import shop.soccerUniform.repository.member.MemberRepository;
import shop.soccerUniform.repository.point.PointRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PointRepository pointRepository;
    private final AddressRepository addressRepository;
    private final BCryptPasswordEncoder bcryptEncoder;

    @Transactional
    @Override
    public void saveMember(MemberSaveForm memberSaveForm) {
        Member member = new Member(memberSaveForm.getBirth(), memberSaveForm.getGender(), Grade.BRONZE,
                memberSaveForm.getMobile(), memberSaveForm.getHomeNum());
        member.addUser(memberSaveForm.getLoginId(), bcryptEncoder.encode(memberSaveForm.getPassword()), memberSaveForm.getUsername(),
                memberSaveForm.getEmail(), Role.ROLE_MEMBER, UserState.ABLE);
        member.addDate(LocalDateTime.now(), LocalDateTime.now());
        memberRepository.save(member);

        // 멤버 주소 생성
        Address address = new Address(member, "기본", memberSaveForm.getPost(), memberSaveForm.getAddr(),
                memberSaveForm.getDetailAddr(), AddressState.ABLE, true);
        address.addDate(LocalDateTime.now(), LocalDateTime.now());
        addressRepository.save(address);

        // 멤버 포인트 생성
        Point point = new Point(member, 0, 0, PointState.ABLE, 1);
        point.addDate(LocalDateTime.now(), LocalDateTime.now());
        pointRepository.save(point);
    }

    @Override
    public boolean duplicateLoginId(String loginId) {
        boolean flag = false;

        Long count = memberRepository.memberCountFindByLoginId(loginId);
        if(count == 0) {
            flag = true;
        }

        return flag;
    }

    @Transactional
    @Override
    public void updateMember(MemberForm memberForm) {
        Optional<Member> optionalMember = memberRepository.findById(memberForm.getMemberId());
        if(optionalMember.isPresent()) {
            Member member = optionalMember.get();
            member.editMember(memberForm.getBirth(), memberForm.getGender(), member.getGrade(), member.getMobile(), member.getHomeNum());
            member.editUser(bcryptEncoder.encode(memberForm.getPassword()), memberForm.getUsername(), memberForm.getEmail(), memberForm.getState());
            member.editDate(LocalDateTime.now());
        }
    }

    @Transactional
    @Override
    public void deletedMember(Long memberId) {
        Optional<Member> findMember = memberRepository.findById(memberId);
        Member member = findMember.orElse(null);
        if(member != null) {
            member.delUser();
        }
    }

    @Override
    public Page<MembersDTO> members(MemberSearchForm memberSearchForm, Pageable pageable) {
        Page<MembersDTO> members = memberRepository.members(memberSearchForm, pageable);
        return members;
    }

    @Override
    public MemberForm memberFindById(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if(member.isEmpty()) {
            throw new RuntimeException("요청하신 회원은 존재하지 않습니다.");
        }

        MemberForm memberForm = new MemberForm();
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
        memberForm.setBirth(member.get().getBirth());
        Point point = pointRepository.findByIdAndState(member.get().getId(), PointState.ABLE);
        memberForm.setPoint(point.getPoint());

        return memberForm;
    }

    @Override
    public MemberForm memberFindByLoginId(String loginId) {
        Optional<Member> optionalMember = memberRepository.findByLoginId(loginId);
        if(optionalMember.isEmpty()) {
            throw new RuntimeException("요청하신 회원은 존재하지 않습니다.");
        }

        Member member = optionalMember.get();
        MemberForm memberForm = new MemberForm();
        memberForm.setMemberId(member.getId());
        memberForm.setLoginId(member.getLoginId());
        memberForm.setUsername(member.getUsername());
        memberForm.setEmail(member.getEmail());
        memberForm.setGender(member.getGender());
        memberForm.setGrade(member.getGrade());
        memberForm.setState(member.getState());
        memberForm.setBirth(member.getBirth());
        memberForm.setMobile(member.getMobile());
        memberForm.setHomeNum(member.getHomeNum());

        return memberForm;
    }

}

