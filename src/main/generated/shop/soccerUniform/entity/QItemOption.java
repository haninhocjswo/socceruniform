package shop.soccerUniform.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemOption is a Querydsl query type for ItemOption
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemOption extends EntityPathBase<ItemOption> {

    private static final long serialVersionUID = 1882618456L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemOption itemOption = new QItemOption("itemOption");

    public final QDateColumns _super = new QDateColumns(this);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QItem item;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    public final StringPath optionName = createString("optionName");

    public final StringPath optionSort = createString("optionSort");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final StringPath valueName = createString("valueName");

    public final StringPath valueSort = createString("valueSort");

    public QItemOption(String variable) {
        this(ItemOption.class, forVariable(variable), INITS);
    }

    public QItemOption(Path<? extends ItemOption> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemOption(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemOption(PathMetadata metadata, PathInits inits) {
        this(ItemOption.class, metadata, inits);
    }

    public QItemOption(Class<? extends ItemOption> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new QItem(forProperty("item"), inits.get("item")) : null;
    }

}

