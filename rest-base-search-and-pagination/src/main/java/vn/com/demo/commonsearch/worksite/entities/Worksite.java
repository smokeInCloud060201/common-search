package vn.com.demo.commonsearch.worksite.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import vn.com.demo.commonsearch.base.BaseEntity;

@Entity
@Table(name = "worksite")
@Getter
@Setter
public class Worksite extends BaseEntity {
    private String location;

    private String zone;
}
