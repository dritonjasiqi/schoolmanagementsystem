package com.auroraschool.backend.service;

import java.util.UUID;

import com.auroraschool.backend.model.Admin;
import com.auroraschool.backend.model.Professor;
import com.auroraschool.backend.model.Student;
import com.auroraschool.backend.model.User;

public interface UserService {
    /**
     * Remove User by Id
     * @param id Id of the user to remove
     */
    void removeUser(UUID id);

    /**
     * Gives the User associated with Username
     * @param username Username of the user that its being search
     * @return User
     */
    User getUserByUsername(String username);
    /**
     * Gives the User associated with Email
     * @param email Email of the user that its being search
     * @return User
     */
    User getUserByEmail(String email);
    /**
     * Gives the User associated with Id
     * @param id Id of the user that its being search
     * @return User
     */
    User getUserById(UUID id);

    /**
     * Update the User with the new values, the user must contain the Id of the user to update
     * @param user User Object with the new values to update, it must contain the Id of the user to update
     * @return User
     */
    User updateUser(User user);

    /**
     * Adds a new student
     * @param student Student Object to add, it must contain all the required fields to create a student
     * @return Student
     */
    Student addStudent(Student student);
    /**
     * Adds a new professor
     * @param professor Professor Object to add, it must contain all the required fields to create a professor
     * @return Professor
     */
    Professor addProfessor(Professor professor);
    /**
     * Adds a new admin
     * @param admin Admin Object to add, it must contain all the required fields to create an admin
     * @return Admin
     */
    Admin addAdmin(Admin admin);
}
