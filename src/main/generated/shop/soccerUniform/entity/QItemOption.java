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

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QItem item;

    public final ListPath<ItemOptionValue, QItemOptionValue> itemOptionValues = this.<ItemOptionValue, QItemOptionValue>createList("itemOptionValues", ItemOptionValue.class, QItemOptionValue.class, PathInits.DIRECT2);

    public final StringPath optionName = createString("optionName");

    public final NumberPath<Integer> optionSort = createNumber("optionSort", Integer.class);

    public final EnumPath<shop.soccerUniform.entity.enumtype.UseYn> useYn = createEnum("useYn", shop.soccerUniform.entity.enumtype.UseYn.class);

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

