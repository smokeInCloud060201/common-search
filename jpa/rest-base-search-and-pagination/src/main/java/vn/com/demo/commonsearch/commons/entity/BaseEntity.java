package vn.com.demo.commonsearch.commons.entity;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import vn.com.demo.commonsearch.commons.annotations.SnowflakeGenerated;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {
    @Id
    @SnowflakeGenerated
    protected Long id;

    @CreationTimestamp
    @Setter(AccessLevel.NONE)
    protected LocalDateTime createdAt;

    @UpdateTimestamp
    @Setter(AccessLevel.NONE)
    protected LocalDateTime updatedAt;

    protected boolean isDeleted;
}
