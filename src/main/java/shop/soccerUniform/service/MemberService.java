package shop.soccerUniform.service;

import shop.soccerUniform.entity.Member;
import shop.soccerUniform.entity.dto.MemberSearchForm;
import shop.soccerUniform.entity.dto.MembersDTO;

import java.util.List;

public interface MemberService {

    List<MembersDTO> members(MemberSearchForm memberSearchForm);
}
