package vn.com.demo.graphql.modules.classes.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.com.demo.graphql.modules.classes.repository.ClassesRepository;

@Service
@RequiredArgsConstructor
public class ClassesService {

    private final ClassesRepository classesRepository;

}
