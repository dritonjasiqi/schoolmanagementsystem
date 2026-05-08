package com.auroraschool.backend.service;

import com.auroraschool.backend.model.Course;

import java.util.UUID;
import java.util.List;

public interface CourseService {
    Course createCourse(Course course, UUID professorId);

    Course getCourse(UUID courseID);

    List<Course> getAllCourses();
}
