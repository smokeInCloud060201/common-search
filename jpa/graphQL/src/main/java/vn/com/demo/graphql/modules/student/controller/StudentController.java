package vn.com.demo.graphql.modules.student.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import vn.com.demo.graphql.commons.dto.BaseResponse;
import vn.com.demo.graphql.commons.mappers.ResponseMapper;
import vn.com.demo.graphql.modules.student.dto.StudentDTO;
import vn.com.demo.graphql.modules.student.services.StudentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StudentController {

    private final StudentService studentService;

    @QueryMapping("SearchResultResponse")
    public BaseResponse<List<StudentDTO.StudentResponseDTO>> searchStudents(@Argument("page") int page) {
        return ResponseMapper.mapResponseToResponseEntity(studentService.getStudents(page));
    }

}
