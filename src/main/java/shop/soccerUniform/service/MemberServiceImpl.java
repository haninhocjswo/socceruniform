package shop.soccerUniform.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.soccerUniform.entity.Member;
import shop.soccerUniform.entity.Point;
import shop.soccerUniform.entity.dto.MemberSearchForm;
import shop.soccerUniform.entity.dto.MembersDTO;
import shop.soccerUniform.repository.member.MemberRepository;
import shop.soccerUniform.repository.point.PointRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PointRepository pointRepository;

    @Override
    public List<MembersDTO> members(MemberSearchForm memberSearchForm) {
        List<MembersDTO> members = memberRepository.members(memberSearchForm);

        if(members.size() > 0) {
            List<Long> memberIds = members.stream()
                    .map(m -> m.getMemberId())
                    .collect(Collectors.toList());
            List<Point> points = pointRepository.findByIds(memberIds);

            for(MembersDTO member : members) {
                for(Point point : points) {
                    if(member.getMemberId() == point.getMember().getId()) {
                        member.setPoint(point.getPoint());
                    }
                }
            }
        }

        return members;
    }
}

