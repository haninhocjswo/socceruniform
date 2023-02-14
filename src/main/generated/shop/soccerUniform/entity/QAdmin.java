package shop.soccerUniform.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAdmin is a Querydsl query type for Admin
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAdmin extends EntityPathBase<Admin> {

    private static final long serialVersionUID = 80693343L;

    public static final QAdmin admin = new QAdmin("admin");

    public final QUser _super = new QUser(this);

    public final StringPath adminNum = createString("adminNum");

    //inherited
    public final StringPath email = _super.email;

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath loginId = _super.loginId;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final StringPath password = _super.password;

    public final StringPath position = createString("position");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final EnumPath<shop.soccerUniform.entity.enumtype.Role> role = _super.role;

    //inherited
    public final EnumPath<shop.soccerUniform.entity.enumtype.UserState> state = _super.state;

    //inherited
    public final StringPath username = _super.username;

    public QAdmin(String variable) {
        super(Admin.class, forVariable(variable));
    }

    public QAdmin(Path<? extends Admin> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdmin(PathMetadata metadata) {
        super(Admin.class, metadata);
    }

}

