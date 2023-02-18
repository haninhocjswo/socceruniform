package shop.soccerUniform.repository.board;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shop.soccerUniform.entity.Board;
import shop.soccerUniform.entity.QBoard;
import shop.soccerUniform.entity.dto.BoardForm;
import shop.soccerUniform.entity.dto.BoardSearchForm;
import shop.soccerUniform.entity.enumtype.BoardType;

import javax.persistence.EntityManager;
import java.util.List;

import static shop.soccerUniform.entity.QBoard.board;

@Repository
public class BoardRepositoryImpl implements BoardQueryRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    @Autowired
    public BoardRepositoryImpl(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<BoardForm> findBoards(BoardSearchForm boardSearchForm) {
        List<BoardForm> notices = queryFactory
                .select(Projections.fields(BoardForm.class,
                        board.id.as("boardId"),
                        board.title,
                        board.content,
                        board.regDate))
                .from(board)
                .where(board.boardType.eq(boardSearchForm.getBoardType()))
                .offset(boardSearchForm.getOffset())
                .limit(boardSearchForm.getLimit())
                .fetch();
        return notices;
    }
}
