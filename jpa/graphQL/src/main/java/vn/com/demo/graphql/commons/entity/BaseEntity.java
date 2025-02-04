package vn.com.demo.graphql.commons.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import vn.com.demo.graphql.commons.annotations.SnowflakeGenerated;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity implements Serializable {

    @Id
    @SnowflakeGenerated
    private Long id;

    @Column
    private boolean isDeleted;

    @Column
    @Getter(AccessLevel.NONE)
    @CreationTimestamp
    private ZonedDateTime createdAt;

    @Column
    @Getter(AccessLevel.NONE)
    @UpdateTimestamp
    private ZonedDateTime updatedAt;
}
