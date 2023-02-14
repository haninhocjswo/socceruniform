package shop.soccerUniform.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QManager is a Querydsl query type for Manager
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QManager extends EntityPathBase<Manager> {

    private static final long serialVersionUID = -2083172163L;

    public static final QManager manager = new QManager("manager");

    public final QUser _super = new QUser(this);

    public final StringPath address = createString("address");

    public final StringPath businessResistNum = createString("businessResistNum");

    public final StringPath ceoName = createString("ceoName");

    public final StringPath chargeMobile = createString("chargeMobile");

    public final StringPath chargeName = createString("chargeName");

    public final StringPath chargeNum = createString("chargeNum");

    public final StringPath chargePosition = createString("chargePosition");

    public final StringPath companyName = createString("companyName");

    public final StringPath companyNum = createString("companyNum");

    public final EnumPath<shop.soccerUniform.entity.enumtype.DeliveryPolicy> deliveryPolicy = createEnum("deliveryPolicy", shop.soccerUniform.entity.enumtype.DeliveryPolicy.class);

    public final StringPath detailAddress = createString("detailAddress");

    //inherited
    public final StringPath email = _super.email;

    public final NumberPath<Double> feePolicyPercent = createNumber("feePolicyPercent", Double.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath loginId = _super.loginId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final StringPath password = _super.password;

    public final NumberPath<Integer> post = createNumber("post", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final EnumPath<shop.soccerUniform.entity.enumtype.Role> role = _super.role;

    //inherited
    public final EnumPath<shop.soccerUniform.entity.enumtype.UserState> state = _super.state;

    //inherited
    public final StringPath username = _super.username;

    public QManager(String variable) {
        super(Manager.class, forVariable(variable));
    }

    public QManager(Path<? extends Manager> path) {
        super(path.getType(), path.getMetadata());
    }

    public QManager(PathMetadata metadata) {
        super(Manager.class, metadata);
    }

}

