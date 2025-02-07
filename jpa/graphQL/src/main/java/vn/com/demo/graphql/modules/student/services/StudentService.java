package vn.com.demo.graphql.modules.student.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import vn.com.demo.graphql.modules.student.dto.StudentDTO;
import vn.com.demo.graphql.modules.student.entity.Student;
import vn.com.demo.graphql.modules.student.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;

    public Page<StudentDTO.StudentResponseDTO> getStudents(int page) {
        log.info("Get students from page {}", page);
        return studentRepository.findAll(PageRequest.of(page, 2)).map(this::mapEntityToDTO);
    }

    private StudentDTO.StudentResponseDTO mapEntityToDTO(Student student) {
        if (student == null) return null;
        return StudentDTO.StudentResponseDTO.builder()
                .id(student.getId().toString())
                .dateOfBirth(Optional.ofNullable(student.getDateOfBirth()).map(LocalDateTime::toString).orElse(null))
                .gender(student.getGender().toString())
                .name(student.getFirstName().concat(" ").concat(student.getLastName()))
                .build();
    }

}
