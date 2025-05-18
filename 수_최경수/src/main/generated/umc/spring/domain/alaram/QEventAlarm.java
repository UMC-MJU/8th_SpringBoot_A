package umc.spring.domain.alaram;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEventAlarm is a Querydsl query type for EventAlarm
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEventAlarm extends EntityPathBase<EventAlarm> {

    private static final long serialVersionUID = -203279407L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEventAlarm eventAlarm = new QEventAlarm("eventAlarm");

    public final QAlarm _super;

    //inherited
    public final EnumPath<umc.spring.domain.enums.AlaramStatus> alaramStatus;

    public final StringPath content = createString("content");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt;

    //inherited
    public final NumberPath<Long> id;

    public final BooleanPath isPermission = createBoolean("isPermission");

    // inherited
    public final umc.spring.domain.QMember member;

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt;

    public QEventAlarm(String variable) {
        this(EventAlarm.class, forVariable(variable), INITS);
    }

    public QEventAlarm(Path<? extends EventAlarm> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEventAlarm(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEventAlarm(PathMetadata metadata, PathInits inits) {
        this(EventAlarm.class, metadata, inits);
    }

    public QEventAlarm(Class<? extends EventAlarm> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QAlarm(type, metadata, inits);
        this.alaramStatus = _super.alaramStatus;
        this.createdAt = _super.createdAt;
        this.id = _super.id;
        this.member = _super.member;
        this.updatedAt = _super.updatedAt;
    }

}

