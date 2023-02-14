package shop.soccerUniform.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccount is a Querydsl query type for Account
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccount extends EntityPathBase<Account> {

    private static final long serialVersionUID = 199215933L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccount account = new QAccount("account");

    public final QDateColumns _super = new QDateColumns(this);

    public final NumberPath<Integer> day = createNumber("day", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QManager manager;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final NumberPath<Integer> month = createNumber("month", Integer.class);

    public final QOrder order;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final NumberPath<Integer> totalFee = createNumber("totalFee", Integer.class);

    public final NumberPath<Integer> totalPaymentAmount = createNumber("totalPaymentAmount", Integer.class);

    public final NumberPath<Integer> totalResultAmount = createNumber("totalResultAmount", Integer.class);

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QAccount(String variable) {
        this(Account.class, forVariable(variable), INITS);
    }

    public QAccount(Path<? extends Account> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAccount(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAccount(PathMetadata metadata, PathInits inits) {
        this(Account.class, metadata, inits);
    }

    public QAccount(Class<? extends Account> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.manager = inits.isInitialized("manager") ? new QManager(forProperty("manager")) : null;
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

