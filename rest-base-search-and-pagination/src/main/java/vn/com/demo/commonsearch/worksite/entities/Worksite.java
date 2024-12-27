package vn.com.demo.commonsearch.worksite.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import vn.com.demo.commonsearch.base.BaseEntity;

@Table(name = "worksite")
@Entity
@Getter
@Setter
public class Worksite extends BaseEntity {
    private String location;

    private long worksiteNumber;
}
