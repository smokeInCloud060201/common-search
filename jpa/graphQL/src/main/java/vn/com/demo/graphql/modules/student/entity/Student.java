package vn.com.demo.graphql.modules.student.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import vn.com.demo.graphql.commons.entity.BaseEntity;
import vn.com.demo.graphql.modules.classes.entity.Classes;
import vn.com.demo.graphql.modules.student.enums.Gender;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "students")
@Entity
@Getter
@Setter
public class Student extends BaseEntity {

    private String firstName;

    private String lastName;

    private LocalDateTime dateOfBirth;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany(mappedBy = "classesList")
    private List<Classes> classesList;
}
