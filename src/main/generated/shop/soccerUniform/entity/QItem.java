package shop.soccerUniform.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = -551332861L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItem item = new QItem("item");

    public final QDateColumns _super = new QDateColumns(this);

    public final QCategory category;

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QManager manager;

    public final StringPath manufacturer = createString("manufacturer");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final StringPath name = createString("name");

    public final EnumPath<shop.soccerUniform.entity.enumtype.OptionType> optionType = createEnum("optionType", shop.soccerUniform.entity.enumtype.OptionType.class);

    public final StringPath origin = createString("origin");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final EnumPath<shop.soccerUniform.entity.enumtype.ItemState> state = createEnum("state", shop.soccerUniform.entity.enumtype.ItemState.class);

    public QItem(String variable) {
        this(Item.class, forVariable(variable), INITS);
    }

    public QItem(Path<? extends Item> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItem(PathMetadata metadata, PathInits inits) {
        this(Item.class, metadata, inits);
    }

    public QItem(Class<? extends Item> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category"), inits.get("category")) : null;
        this.manager = inits.isInitialized("manager") ? new QManager(forProperty("manager")) : null;
    }

}

