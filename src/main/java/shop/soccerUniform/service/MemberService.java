package shop.soccerUniform.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.soccerUniform.entity.Member;
import shop.soccerUniform.entity.dto.MemberForm;
import shop.soccerUniform.entity.dto.MemberSearchForm;
import shop.soccerUniform.entity.dto.MembersDTO;

import java.util.List;
import java.util.Map;

public interface MemberService {

    void saveMember(MemberForm memberForm);

    void updateMember(MemberForm memberForm);

    void deletedMember(Long memberId);

    Page<MembersDTO> members(MemberSearchForm memberSearchForm, Pageable pageable);

    MemberForm findMember(Long memberId);

}
