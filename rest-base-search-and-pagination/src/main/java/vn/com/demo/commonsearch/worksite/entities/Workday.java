package vn.com.demo.commonsearch.worksite.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import vn.com.demo.commonsearch.anno.IncludeSearchKey;
import vn.com.demo.commonsearch.base.BaseEntity;

@Entity
@Table(name = "workday")
@Getter
@Setter
public class Workday extends BaseEntity {

    private String tbm;

    private boolean isNightWork;

    @ManyToOne
    @IncludeSearchKey(value = {"worksite.location"})
    @JoinColumn(name = "worksite_id")
    private Worksite worksite;
}
