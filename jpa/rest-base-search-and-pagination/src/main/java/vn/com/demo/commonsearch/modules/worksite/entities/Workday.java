package vn.com.demo.commonsearch.modules.worksite.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import vn.com.demo.commonsearch.commons.annotations.IncludeSearchKey;
import vn.com.demo.commonsearch.commons.entity.BaseEntity;

@Entity
@Table(name = "workday")
@Getter
@Setter
public class Workday extends BaseEntity {

    @IncludeSearchKey
    private String tbm;

    @IncludeSearchKey
    private String inspection;

    private boolean isNightWork;

    @ManyToOne
    @IncludeSearchKey(value = {"location", "zone"})
    @JoinColumn(name = "worksite_id")
    private Worksite worksite;
}
