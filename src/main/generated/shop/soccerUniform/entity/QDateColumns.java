package shop.soccerUniform.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDateColumns is a Querydsl query type for DateColumns
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QDateColumns extends EntityPathBase<DateColumns> {

    private static final long serialVersionUID = -2135156385L;

    public static final QDateColumns dateColumns = new QDateColumns("dateColumns");

    public final DateTimePath<java.time.LocalDateTime> modDate = createDateTime("modDate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public QDateColumns(String variable) {
        super(DateColumns.class, forVariable(variable));
    }

    public QDateColumns(Path<? extends DateColumns> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDateColumns(PathMetadata metadata) {
        super(DateColumns.class, metadata);
    }

}

