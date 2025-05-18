package umc.spring.domain.alaram;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQnaAlarm is a Querydsl query type for QnaAlarm
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQnaAlarm extends EntityPathBase<QnaAlarm> {

    private static final long serialVersionUID = 1179002183L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQnaAlarm qnaAlarm = new QQnaAlarm("qnaAlarm");

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

    public QQnaAlarm(String variable) {
        this(QnaAlarm.class, forVariable(variable), INITS);
    }

    public QQnaAlarm(Path<? extends QnaAlarm> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQnaAlarm(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQnaAlarm(PathMetadata metadata, PathInits inits) {
        this(QnaAlarm.class, metadata, inits);
    }

    public QQnaAlarm(Class<? extends QnaAlarm> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this._super = new QAlarm(type, metadata, inits);
        this.alaramStatus = _super.alaramStatus;
        this.createdAt = _super.createdAt;
        this.id = _super.id;
        this.member = _super.member;
        this.updatedAt = _super.updatedAt;
    }

}

