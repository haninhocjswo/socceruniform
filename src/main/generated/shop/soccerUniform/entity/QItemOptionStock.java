package shop.soccerUniform.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemOptionStock is a Querydsl query type for ItemOptionStock
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemOptionStock extends EntityPathBase<ItemOptionStock> {

    private static final long serialVersionUID = 196625310L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemOptionStock itemOptionStock = new QItemOptionStock("itemOptionStock");

    public final QDateColumns _super = new QDateColumns(this);

    public final QItemOption firstItemOption;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modDate = _super.modDate;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> regDate = _super.regDate;

    public final QItemOption secondItemOption;

    public final NumberPath<Integer> stock = createNumber("stock", Integer.class);

    public QItemOptionStock(String variable) {
        this(ItemOptionStock.class, forVariable(variable), INITS);
    }

    public QItemOptionStock(Path<? extends ItemOptionStock> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemOptionStock(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemOptionStock(PathMetadata metadata, PathInits inits) {
        this(ItemOptionStock.class, metadata, inits);
    }

    public QItemOptionStock(Class<? extends ItemOptionStock> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.firstItemOption = inits.isInitialized("firstItemOption") ? new QItemOption(forProperty("firstItemOption"), inits.get("firstItemOption")) : null;
        this.secondItemOption = inits.isInitialized("secondItemOption") ? new QItemOption(forProperty("secondItemOption"), inits.get("secondItemOption")) : null;
    }

}

