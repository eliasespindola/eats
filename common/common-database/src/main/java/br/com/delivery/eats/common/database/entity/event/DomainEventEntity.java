package br.com.delivery.eats.common.database.entity.event;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@ToString
@Table(name = "DOMAIN_EVENT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE", discriminatorType = DiscriminatorType.STRING)
public abstract class DomainEventEntity<T> extends AbstractAggregateRoot<DomainEventEntity<T>> {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "ID")
    private UUID id;

    @Getter
    @NotNull
    @Size(max = 100)
    @Column(name = "CORRELATION_ID", unique = true, updatable = false)
    private final String correlationId;

    @Getter
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false, insertable = false, updatable = false)
    private final DomainEventType type;

    @Getter
    @NotNull
    @Column(name = "CREATED_AT", updatable = false)
    private final ZonedDateTime createdAt = ZonedDateTime.now();

    protected DomainEventEntity(String correlationId, DomainEventType type) {
        this.correlationId = correlationId;
        this.type = type;
    }

    public DomainEventEntity() {
        this(null,null);
    }

    public abstract T getSource();
    @PrePersist
    public void prePersist() {
        registerEvent(this);
    }
}
