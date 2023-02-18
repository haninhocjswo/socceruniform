package shop.soccerUniform.repository.board;

import shop.soccerUniform.entity.Board;
import shop.soccerUniform.entity.dto.BoardForm;
import shop.soccerUniform.entity.dto.BoardSearchForm;

import java.util.List;

public interface BoardQueryRepository {
    List<BoardForm> findBoards(BoardSearchForm boardSearchForm);
}
