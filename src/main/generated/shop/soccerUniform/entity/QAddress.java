package shop.soccerUniform.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAddress is a Querydsl query type for Address
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAddress extends EntityPathBase<Address> {

    private static final long serialVersionUID = 228842756L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAddress address = new QAddress("address");

    public final QDateColumns _super = new QDateColumns(this);

    public final StringPath addr = createString("addr");

    public final StringPath detailAddr = createString("detailAddr");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isBase = createBoolean("isBase");

    public final QMember member;

    public final StringPath memo = createString("memo");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final NumberPath<Integer> post = createNumber("post", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final EnumPath<shop.soccerUniform.entity.enumtype.AddressState> state = createEnum("state", shop.soccerUniform.entity.enumtype.AddressState.class);

    public QAddress(String variable) {
        this(Address.class, forVariable(variable), INITS);
    }

    public QAddress(Path<? extends Address> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAddress(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAddress(PathMetadata metadata, PathInits inits) {
        this(Address.class, metadata, inits);
    }

    public QAddress(Class<? extends Address> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

