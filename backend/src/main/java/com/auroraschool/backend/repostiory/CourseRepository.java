package com.auroraschool.backend.repostiory;

import com.auroraschool.backend.model.Course;
import com.auroraschool.backend.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    List<Course> findByProfessor(Professor professor);
}
