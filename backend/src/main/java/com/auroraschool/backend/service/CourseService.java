package com.auroraschool.backend.service;

import com.auroraschool.backend.model.Course;

import java.util.UUID;
import java.util.List;

/**
 * Interface that defines the methods for the CourseServiceClass
 */
public interface CourseService {
    /**
     * Creates a Course and associate it to a professor
     * @param course Course Object
     * @param professorId Id of the professor to associate the course to
     * @return the created Course
     */
    Course createCourse(Course course, UUID professorId);

    /**
     * Returns the Course
     * @param courseID Id of the course to search
     * @return the Course associated to the courseID
     */
    Course getCourse(UUID courseID);

    /**
     * @return All the courses stored in the Database
     */
    List<Course> getAllCourses();
}
