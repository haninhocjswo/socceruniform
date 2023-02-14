package shop.soccerUniform.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDelivery is a Querydsl query type for Delivery
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDelivery extends EntityPathBase<Delivery> {

    private static final long serialVersionUID = 546372836L;

    public static final QDelivery delivery = new QDelivery("delivery");

    public final QDateColumns _super = new QDateColumns(this);

    public final StringPath addr = createString("addr");

    public final EnumPath<shop.soccerUniform.entity.enumtype.DeliveryCompany> deliveryCompany = createEnum("deliveryCompany", shop.soccerUniform.entity.enumtype.DeliveryCompany.class);

    public final NumberPath<Integer> deliveryNum = createNumber("deliveryNum", Integer.class);

    public final StringPath detailAddr = createString("detailAddr");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final StringPath post = createString("post");

    public final StringPath receiveNum = createString("receiveNum");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final EnumPath<shop.soccerUniform.entity.enumtype.DeliveryState> state = createEnum("state", shop.soccerUniform.entity.enumtype.DeliveryState.class);

    public QDelivery(String variable) {
        super(Delivery.class, forVariable(variable));
    }

    public QDelivery(Path<? extends Delivery> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDelivery(PathMetadata metadata) {
        super(Delivery.class, metadata);
    }

}

