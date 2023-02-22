package shop.soccerUniform.repository.member;

import shop.soccerUniform.entity.Member;
import shop.soccerUniform.entity.dto.MemberSearchForm;
import shop.soccerUniform.entity.dto.MembersDTO;

import java.util.List;

public interface MemberQueryRepository {

    List<MembersDTO> members(MemberSearchForm memberSearchForm);
    Long countMembers(MemberSearchForm memberSearchForm);
}
