package com.auroraschool.backend.service;

import com.auroraschool.backend.model.Course;

import java.util.UUID;
import java.util.List;

/**
 * Service layer interface defining core business logic contracts for managing {@link Course} entities.
 * <p>
 * This contract establishes operational boundaries for listing existing courses, looking up
 * individual module profiles, and onboarding new courses into the system under verified faculty oversight.
 * </p>
 *
 * @author Driton Jasiqi
 * @see Course
 * @see UUID
 */
public interface CourseService {

    /**
     * Instantiates a new course entry and creates an ownership link to a designated professor.
     *
     * @param course      the unpersisted {@link Course} template structural data sent by the client
     * @param professorId the unique {@link UUID} tracking the faculty member teaching this course
     * @return the fully persisted and managed {@link Course} instance containing database identifiers
     */
    Course createCourse(Course course, UUID professorId);

    /**
     * Locates a single course profile based on its unique relational database token.
     *
     * @param courseID the unique {@link UUID} key tracking the desired course record
     * @return the matching {@link Course} entity context located by the system lookup routines
     */
    Course getCourse(UUID courseID);

    /**
     * Compiles an unpaginated summary index of every course currently stored within the database.
     *
     * @return a {@link List} of all registered {@link Course} entities, which may be empty if no rows are stored
     */
    List<Course> getAllCourses();
}