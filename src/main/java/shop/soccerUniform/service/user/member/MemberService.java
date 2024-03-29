package shop.soccerUniform.service.user.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.soccerUniform.entity.dto.MemberForm;
import shop.soccerUniform.entity.dto.MemberSaveForm;
import shop.soccerUniform.entity.dto.MemberSearchForm;
import shop.soccerUniform.entity.dto.MembersDTO;

public interface MemberService {

    void saveMember(MemberSaveForm memberSaveForm);

    boolean duplicateLoginId(String loginId);

    void updateMember(MemberForm memberForm);

    void deletedMember(Long memberId);

    Page<MembersDTO> members(MemberSearchForm memberSearchForm, Pageable pageable);

    MemberForm memberFindById(Long memberId);

    MemberForm memberFindByLoginId(String loginId);
}
