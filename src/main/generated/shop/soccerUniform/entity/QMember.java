package shop.soccerUniform.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1449007222L;

    public static final QMember member = new QMember("member1");

    public final QUser _super = new QUser(this);

    //inherited
    public final StringPath email = _super.email;

    public final EnumPath<shop.soccerUniform.entity.enumtype.Gender> gender = createEnum("gender", shop.soccerUniform.entity.enumtype.Gender.class);

    public final EnumPath<shop.soccerUniform.entity.enumtype.Grade> grade = createEnum("grade", shop.soccerUniform.entity.enumtype.Grade.class);

    public final StringPath homeNum = createString("homeNum");

    //inherited
    public final NumberPath<Long> id = _super.id;

    //inherited
    public final StringPath loginId = _super.loginId;

    public final StringPath mobile = createString("mobile");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final StringPath password = _super.password;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    //inherited
    public final EnumPath<shop.soccerUniform.entity.enumtype.Role> role = _super.role;

    //inherited
    public final EnumPath<shop.soccerUniform.entity.enumtype.UserState> state = _super.state;

    //inherited
    public final StringPath username = _super.username;

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

