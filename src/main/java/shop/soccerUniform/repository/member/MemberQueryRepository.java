package shop.soccerUniform.repository.member;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import shop.soccerUniform.entity.Member;
import shop.soccerUniform.entity.dto.MemberSearchForm;
import shop.soccerUniform.entity.dto.MembersDTO;

import java.util.List;
import java.util.Map;

public interface MemberQueryRepository {

    Page<MembersDTO> members(MemberSearchForm memberSearchForm, Pageable pageable);
    Long findByLoginId(String loginId);
}
