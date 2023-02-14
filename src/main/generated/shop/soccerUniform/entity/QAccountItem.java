package shop.soccerUniform.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccountItem is a Querydsl query type for AccountItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccountItem extends EntityPathBase<AccountItem> {

    private static final long serialVersionUID = 880858096L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccountItem accountItem = new QAccountItem("accountItem");

    public final QDateColumns _super = new QDateColumns(this);

    public final QAccount account;

    public final NumberPath<Integer> fee = createNumber("fee", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final QOrderItem orderItem;

    public final QOrderItemCancel orderItemCancel;

    public final NumberPath<Integer> paymentAmount = createNumber("paymentAmount", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final NumberPath<Integer> resultAmount = createNumber("resultAmount", Integer.class);

    public QAccountItem(String variable) {
        this(AccountItem.class, forVariable(variable), INITS);
    }

    public QAccountItem(Path<? extends AccountItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAccountItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAccountItem(PathMetadata metadata, PathInits inits) {
        this(AccountItem.class, metadata, inits);
    }

    public QAccountItem(Class<? extends AccountItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.account = inits.isInitialized("account") ? new QAccount(forProperty("account"), inits.get("account")) : null;
        this.orderItem = inits.isInitialized("orderItem") ? new QOrderItem(forProperty("orderItem"), inits.get("orderItem")) : null;
        this.orderItemCancel = inits.isInitialized("orderItemCancel") ? new QOrderItemCancel(forProperty("orderItemCancel"), inits.get("orderItemCancel")) : null;
    }

}

