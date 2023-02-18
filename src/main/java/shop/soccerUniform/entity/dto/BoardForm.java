package shop.soccerUniform.entity.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class BoardForm {

    private Long boardId;
    private Integer rowNum;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime regDate;
}
