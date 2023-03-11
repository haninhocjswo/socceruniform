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

    public final QItemOptionValue firstOptionValue;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QItem item;

    public final QItemOptionValue secondOptionValue;

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
        this.firstOptionValue = inits.isInitialized("firstOptionValue") ? new QItemOptionValue(forProperty("firstOptionValue"), inits.get("firstOptionValue")) : null;
        this.item = inits.isInitialized("item") ? new QItem(forProperty("item"), inits.get("item")) : null;
        this.secondOptionValue = inits.isInitialized("secondOptionValue") ? new QItemOptionValue(forProperty("secondOptionValue"), inits.get("secondOptionValue")) : null;
    }

}

