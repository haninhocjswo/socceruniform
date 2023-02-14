package shop.soccerUniform.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPoint is a Querydsl query type for Point
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPoint extends EntityPathBase<Point> {

    private static final long serialVersionUID = 94870176L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPoint point1 = new QPoint("point1");

    public final QDateColumns _super = new QDateColumns(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final NumberPath<Integer> sort = createNumber("sort", Integer.class);

    public final EnumPath<shop.soccerUniform.entity.enumtype.PointState> state = createEnum("state", shop.soccerUniform.entity.enumtype.PointState.class);

    public final NumberPath<Integer> updatePoint = createNumber("updatePoint", Integer.class);

    public QPoint(String variable) {
        this(Point.class, forVariable(variable), INITS);
    }

    public QPoint(Path<? extends Point> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPoint(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPoint(PathMetadata metadata, PathInits inits) {
        this(Point.class, metadata, inits);
    }

    public QPoint(Class<? extends Point> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

