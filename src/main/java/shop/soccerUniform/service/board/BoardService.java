package shop.soccerUniform.service.board;

import shop.soccerUniform.entity.dto.BoardForm;
import shop.soccerUniform.entity.dto.BoardSearchForm;

import java.util.List;

public interface BoardService {
    List<BoardForm> notices(BoardSearchForm boardSearchForm);
}
