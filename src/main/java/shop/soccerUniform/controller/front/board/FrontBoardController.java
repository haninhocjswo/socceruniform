package shop.soccerUniform.controller.front.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import shop.soccerUniform.entity.dto.BoardForm;
import shop.soccerUniform.entity.dto.BoardSearchForm;
import shop.soccerUniform.entity.enumtype.BoardType;
import shop.soccerUniform.service.board.BoardService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class FrontBoardController {

    private final BoardService boardService;

    @GetMapping("/notices")
    public String notice(@ModelAttribute(name = "boardSearchForm") BoardSearchForm boardSearchForm, Model model) {
        boardSearchForm.setBoardType(BoardType.NOTICE);
        List<BoardForm> notices = boardService.notices(boardSearchForm);
        model.addAttribute("notices",notices);
        model.addAttribute("boardSearchForm", boardSearchForm);
        return "front/board/notices";
    }
}
