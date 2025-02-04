package vn.com.demo.graphql.modules.classes.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import vn.com.demo.graphql.commons.entity.BaseEntity;
import vn.com.demo.graphql.modules.student.entity.Student;

import java.util.List;

@Table(name = "classes")
@Entity
@Getter
@Setter
public class Classes extends BaseEntity {

    private String className;

    private String teacherName;

    private boolean isDeleted;

    @ManyToMany
    @JoinTable(name = "classes_student",
            joinColumns = @JoinColumn(name = "classes_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> classesList;

}
