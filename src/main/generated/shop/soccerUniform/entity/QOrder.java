package shop.soccerUniform.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = 94030942L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order1");

    public final QDateColumns _super = new QDateColumns(this);

    public final QDelivery delivery;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final StringPath OrderNum = createString("OrderNum");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final EnumPath<shop.soccerUniform.entity.enumtype.OrderState> state = createEnum("state", shop.soccerUniform.entity.enumtype.OrderState.class);

    public final NumberPath<Integer> totalEventSalePrice = createNumber("totalEventSalePrice", Integer.class);

    public final NumberPath<Integer> totalPointSalePrice = createNumber("totalPointSalePrice", Integer.class);

    public final NumberPath<Integer> totalPrice = createNumber("totalPrice", Integer.class);

    public final NumberPath<Integer> totalSalePrice = createNumber("totalSalePrice", Integer.class);

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder(PathMetadata metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.delivery = inits.isInitialized("delivery") ? new QDelivery(forProperty("delivery")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

