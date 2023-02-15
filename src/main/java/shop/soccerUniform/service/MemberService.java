package shop.soccerUniform.service;

import shop.soccerUniform.entity.Member;
import shop.soccerUniform.entity.dto.MemberForm;
import shop.soccerUniform.entity.dto.MemberSearchForm;
import shop.soccerUniform.entity.dto.MembersDTO;

import java.util.List;

public interface MemberService {

    void saveMember(MemberForm memberForm);

    List<MembersDTO> members(MemberSearchForm memberSearchForm);

    MemberForm findMember(Long memberId);
}
