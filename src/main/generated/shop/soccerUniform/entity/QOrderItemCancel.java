package shop.soccerUniform.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderItemCancel is a Querydsl query type for OrderItemCancel
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderItemCancel extends EntityPathBase<OrderItemCancel> {

    private static final long serialVersionUID = 154535531L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderItemCancel orderItemCancel = new QOrderItemCancel("orderItemCancel");

    public final QDateColumns _super = new QDateColumns(this);

    public final NumberPath<Integer> cancelEventSalePrice = createNumber("cancelEventSalePrice", Integer.class);

    public final NumberPath<Integer> cancelPointSalePrice = createNumber("cancelPointSalePrice", Integer.class);

    public final NumberPath<Integer> cancelPrice = createNumber("cancelPrice", Integer.class);

    public final NumberPath<Integer> cancelQuantity = createNumber("cancelQuantity", Integer.class);

    public final NumberPath<Integer> cancelSalePrice = createNumber("cancelSalePrice", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final QOrderItem orderItem;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public QOrderItemCancel(String variable) {
        this(OrderItemCancel.class, forVariable(variable), INITS);
    }

    public QOrderItemCancel(Path<? extends OrderItemCancel> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderItemCancel(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderItemCancel(PathMetadata metadata, PathInits inits) {
        this(OrderItemCancel.class, metadata, inits);
    }

    public QOrderItemCancel(Class<? extends OrderItemCancel> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.orderItem = inits.isInitialized("orderItem") ? new QOrderItem(forProperty("orderItem"), inits.get("orderItem")) : null;
    }

}

