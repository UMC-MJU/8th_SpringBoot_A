package umc.spring.domain.alaram;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReviewAlarm is a Querydsl query type for ReviewAlarm
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReviewAlarm extends EntityPathBase<ReviewAlarm> {

    private static final long serialVersionUID = -258646753L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReviewAlarm reviewAlarm = new QReviewAlarm("reviewAlarm");

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

    public QReviewAlarm(String variable) {
        this(ReviewAlarm.class, forVariable(variable), INITS);
    }

    public QReviewAlarm(Path<? extends ReviewAlarm> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReviewAlarm(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReviewAlarm(PathMetadata metadata, PathInits inits) {
        this(ReviewAlarm.class, metadata, inits);
    }

    public QReviewAlarm(Class<? extends ReviewAlarm> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QAlarm(type, metadata, inits);
        this.alaramStatus = _super.alaramStatus;
        this.createdAt = _super.createdAt;
        this.id = _super.id;
        this.member = _super.member;
        this.updatedAt = _super.updatedAt;
    }

}

