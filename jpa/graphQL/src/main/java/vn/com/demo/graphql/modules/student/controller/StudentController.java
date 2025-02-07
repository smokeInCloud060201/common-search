package vn.com.demo.graphql.modules.student.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import vn.com.demo.graphql.commons.dto.BaseResponse;
import vn.com.demo.graphql.modules.student.services.StudentService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final StudentService studentService;

    @QueryMapping("searchStudents")
    public BaseResponse<Object> searchStudents(@Argument("page") int page) {
        return BaseResponse.builder()
                .data(123)
                .build();
    }

    @QueryMapping("searchStudents1")
    public BaseResponse<Object> searchStudents1(@Argument("page") int page) {
        return BaseResponse.builder()
                .data(123)
                .build();
    }

}
