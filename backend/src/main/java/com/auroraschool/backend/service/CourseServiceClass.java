package com.auroraschool.backend.service;

import com.auroraschool.backend.model.Course;
import com.auroraschool.backend.model.Professor;
import com.auroraschool.backend.repostiory.CourseRepository;
import com.auroraschool.backend.repostiory.ProfessorRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceClass implements CourseService {
    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;

    @Override
    public Course createCourse(Course course, UUID professorId) throws IllegalArgumentException {
        Professor professor = professorRepository.findById(professorId).orElseThrow(() -> new IllegalArgumentException("Professor not found"));
        course.setProfessor(professor);
        return courseRepository.save(course);
    }

    @Override
    public Course getCourse(UUID courseID)  throws IllegalArgumentException{
        return courseRepository.findById(courseID).orElseThrow(() -> new IllegalArgumentException("Course not found"));
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
}
