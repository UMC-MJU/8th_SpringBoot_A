package umc.study.service.customerService;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCustomerPrefer is a Querydsl query type for CustomerPrefer
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCustomerPrefer extends EntityPathBase<CustomerPrefer> {

    private static final long serialVersionUID = 1666762726L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCustomerPrefer customerPrefer = new QCustomerPrefer("customerPrefer");

    public final umc.study.domain.common.QBaseEntity _super = new umc.study.domain.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final umc.study.domain.QCustomer customer;

    public final umc.study.domain.QCategories foodCategory;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QCustomerPrefer(String variable) {
        this(CustomerPrefer.class, forVariable(variable), INITS);
    }

    public QCustomerPrefer(Path<? extends CustomerPrefer> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCustomerPrefer(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCustomerPrefer(PathMetadata metadata, PathInits inits) {
        this(CustomerPrefer.class, metadata, inits);
    }

    public QCustomerPrefer(Class<? extends CustomerPrefer> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.customer = inits.isInitialized("customer") ? new umc.study.domain.QCustomer(forProperty("customer")) : null;
        this.foodCategory = inits.isInitialized("foodCategory") ? new umc.study.domain.QCategories(forProperty("foodCategory")) : null;
    }

}

