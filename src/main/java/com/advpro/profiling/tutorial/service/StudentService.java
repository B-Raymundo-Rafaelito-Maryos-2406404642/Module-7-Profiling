package com.advpro.profiling.tutorial.service;

import com.advpro.profiling.tutorial.model.Student;
import com.advpro.profiling.tutorial.model.StudentCourse;
import com.advpro.profiling.tutorial.repository.StudentCourseRepository;
import com.advpro.profiling.tutorial.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    public List<StudentCourse> getAllStudentsWithCourses() {
        // Use single query with JOIN FETCH to avoid N+1 queries and let JPA fetch student and course together
        return studentCourseRepository.findAllWithStudentAndCourse();
    }

    public Optional<Student> findStudentWithHighestGpa() {
        // Delegate to repository to let the database find the top GPA student
        return studentRepository.findTopByOrderByGpaDesc();
    }

    public String joinStudentNames() {
        // Fetch only names from DB and join using String.join to avoid repeated string concatenation
        List<String> names = studentRepository.findAllNames();
        if (names == null || names.isEmpty()) {
            return "";
        }
        return String.join(", ", names);
    }
}

