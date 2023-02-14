package shop.soccerUniform.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrderItem is a Querydsl query type for OrderItem
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrderItem extends EntityPathBase<OrderItem> {

    private static final long serialVersionUID = -391881583L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrderItem orderItem = new QOrderItem("orderItem");

    public final QDateColumns _super = new QDateColumns(this);

    public final QEvent event;

    public final NumberPath<Integer> eventSalePrice = createNumber("eventSalePrice", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QItem item;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final QOrder order;

    public final QPoint point;

    public final NumberPath<Integer> pointSalePrice = createNumber("pointSalePrice", Integer.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> quantity = createNumber("quantity", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final NumberPath<Integer> salePrice = createNumber("salePrice", Integer.class);

    public final EnumPath<shop.soccerUniform.entity.enumtype.OrderState> state = createEnum("state", shop.soccerUniform.entity.enumtype.OrderState.class);

    public QOrderItem(String variable) {
        this(OrderItem.class, forVariable(variable), INITS);
    }

    public QOrderItem(Path<? extends OrderItem> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrderItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrderItem(PathMetadata metadata, PathInits inits) {
        this(OrderItem.class, metadata, inits);
    }

    public QOrderItem(Class<? extends OrderItem> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.event = inits.isInitialized("event") ? new QEvent(forProperty("event"), inits.get("event")) : null;
        this.item = inits.isInitialized("item") ? new QItem(forProperty("item"), inits.get("item")) : null;
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
        this.point = inits.isInitialized("point") ? new QPoint(forProperty("point"), inits.get("point")) : null;
    }

}

