package shop.soccerUniform.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBoard is a Querydsl query type for Board
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBoard extends EntityPathBase<Board> {

    private static final long serialVersionUID = 81933302L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBoard board = new QBoard("board");

    public final QDateColumns _super = new QDateColumns(this);

    public final EnumPath<shop.soccerUniform.entity.enumtype.BoardType> boardType = createEnum("boardType", shop.soccerUniform.entity.enumtype.BoardType.class);

    public final StringPath content = createString("content");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final QBoard parent;

    public final StringPath password = createString("password");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final EnumPath<shop.soccerUniform.entity.enumtype.BoardState> state = createEnum("state", shop.soccerUniform.entity.enumtype.BoardState.class);

    public final StringPath title = createString("title");

    public final QUser writer;

    public QBoard(String variable) {
        this(Board.class, forVariable(variable), INITS);
    }

    public QBoard(Path<? extends Board> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBoard(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBoard(PathMetadata metadata, PathInits inits) {
        this(Board.class, metadata, inits);
    }

    public QBoard(Class<? extends Board> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.parent = inits.isInitialized("parent") ? new QBoard(forProperty("parent"), inits.get("parent")) : null;
        this.writer = inits.isInitialized("writer") ? new QUser(forProperty("writer")) : null;
    }

}

