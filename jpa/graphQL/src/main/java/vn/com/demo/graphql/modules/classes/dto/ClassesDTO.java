package vn.com.demo.graphql.modules.classes.dto;

import lombok.Builder;
import lombok.Getter;
import vn.com.demo.graphql.commons.annotations.GraphQLTypeMapping;
import vn.com.demo.graphql.commons.dto.AbstractResponse;
import vn.com.demo.graphql.modules.student.dto.StudentDTO;

import java.util.List;

public interface ClassesDTO {

    @Builder
    @Getter
    @GraphQLTypeMapping("Classes")
    class ClassesResponseDTO extends AbstractResponse {
        private String id;
        private String teacherName;
        private String className;
        private List<StudentDTO.StudentResponseDTO> studentList;
    }
}
