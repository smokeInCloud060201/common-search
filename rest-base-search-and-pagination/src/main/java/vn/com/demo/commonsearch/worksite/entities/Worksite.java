package vn.com.demo.commonsearch.worksite.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import vn.com.demo.commonsearch.base.BaseEntity;

@Table(name = "worksite")
@Entity
public class Worksite extends BaseEntity {
    private String location;
}
