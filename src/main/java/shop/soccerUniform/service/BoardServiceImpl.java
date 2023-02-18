package shop.soccerUniform.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.soccerUniform.entity.dto.BoardForm;
import shop.soccerUniform.entity.dto.BoardSearchForm;
import shop.soccerUniform.repository.board.BoardRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;


    @Override
    public List<BoardForm> notices(BoardSearchForm boardSearchForm) {
        return boardRepository.findBoards(boardSearchForm);
    }
}
