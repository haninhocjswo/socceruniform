package shop.soccerUniform.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.soccerUniform.entity.enumtype.BoardState;
import shop.soccerUniform.entity.enumtype.BoardType;

import javax.persistence.*;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "T_BOARD")
@Getter
@NoArgsConstructor(access = PROTECTED)
public class Board extends DateColumns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WRITER_ID", nullable = false)
    private User writer;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BoardType boardType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BoardState state;

    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    private Board parent;

    public Board(String title, String content, User writer, BoardType boardType, BoardState state, String password, Board parent) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.boardType = boardType;
        this.state = state;
        this.password = password;
        this.parent = parent;
    }
}
