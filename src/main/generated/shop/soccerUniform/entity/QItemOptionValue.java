package shop.soccerUniform.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItemOptionValue is a Querydsl query type for ItemOptionValue
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemOptionValue extends EntityPathBase<ItemOptionValue> {

    private static final long serialVersionUID = 198827513L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItemOptionValue itemOptionValue = new QItemOptionValue("itemOptionValue");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QItem item;

    public final QItemOption itemOption;

    public final StringPath optionValue = createString("optionValue");

    public final NumberPath<Integer> optionValueSort = createNumber("optionValueSort", Integer.class);

    public final EnumPath<shop.soccerUniform.entity.enumtype.UseYn> useYn = createEnum("useYn", shop.soccerUniform.entity.enumtype.UseYn.class);

    public QItemOptionValue(String variable) {
        this(ItemOptionValue.class, forVariable(variable), INITS);
    }

    public QItemOptionValue(Path<? extends ItemOptionValue> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItemOptionValue(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItemOptionValue(PathMetadata metadata, PathInits inits) {
        this(ItemOptionValue.class, metadata, inits);
    }

    public QItemOptionValue(Class<? extends ItemOptionValue> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.item = inits.isInitialized("item") ? new QItem(forProperty("item"), inits.get("item")) : null;
        this.itemOption = inits.isInitialized("itemOption") ? new QItemOption(forProperty("itemOption"), inits.get("itemOption")) : null;
    }

}

