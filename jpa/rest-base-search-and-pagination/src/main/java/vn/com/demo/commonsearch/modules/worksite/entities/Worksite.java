package vn.com.demo.commonsearch.modules.worksite.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.com.demo.commonsearch.commons.entity.BaseEntity;

@Entity
@Table(name = "worksite")
@Getter
@Setter
public class Worksite extends BaseEntity {
    private String location;

    private String zone;

    private boolean isNightWork;

    @Enumerated(EnumType.STRING)
    private SiteStatus siteStatus;

}
