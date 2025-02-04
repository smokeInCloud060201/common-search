package vn.com.demo.graphql.modules.student.dto;

import lombok.Builder;
import lombok.Getter;
import vn.com.demo.graphql.commons.annotations.GraphQLTypeMapping;
import vn.com.demo.graphql.commons.dto.AbstractResponse;

public interface StudentDTO {

    @Builder
    @Getter
    @GraphQLTypeMapping("Student")
    class StudentResponseDTO extends AbstractResponse {
        private String id;
        private String name;
        private String dateOfBirth;
        private String gender;
    }
}
