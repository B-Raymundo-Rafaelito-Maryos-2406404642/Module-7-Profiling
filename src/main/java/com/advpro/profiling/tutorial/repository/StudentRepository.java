package com.advpro.profiling.tutorial.repository;

import com.advpro.profiling.tutorial.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	Optional<Student> findTopByOrderByGpaDesc();

	@Query("SELECT s.name FROM Student s")
	List<String> findAllNames();
    
    // @Query("SELECT s FROM Student s JOIN FETCH s.studentCourses sc JOIN FETCH sc.course c")
    // List<Student> findAllWithCourses();
}