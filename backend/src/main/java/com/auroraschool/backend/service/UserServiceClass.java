package com.auroraschool.backend.service;

import com.auroraschool.backend.model.*;
import com.auroraschool.backend.repostiory.ProfessorRepository;
import com.auroraschool.backend.repostiory.UserRepository;
import com.auroraschool.backend.repostiory.StudentRepository;
import com.auroraschool.backend.exception.EmailExistException;

// It wraps Methods execution into a Database transaction, if an Exception thrown rollback
// and if not than the Transaction is commited
import jakarta.transaction.Transactional;
// Generates a constructor for all final fields, enforcing clean constructor injection.
import lombok.RequiredArgsConstructor;
// Package that tell Spring this class is a Service containing Business Logic
// and it will be automatically detected and registered as a Bean in the Spring context
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service for User entity with business operations to create, retrieve, and list users.
 * Associates users with roles and executes repository operations within
 * transactional boundaries to ensure consistency.
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceClass implements UserService {
    private final UserRepository userRepository;
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;


    /**
     * Remove the user associated with id
     * @param id Id of the user to remove
     * @throws IllegalAccessError
     */
    @Override
    public void removeUser(UUID id) throws IllegalAccessError {
        if(!userRepository.existsById(id))
            throw new IllegalArgumentException(String.format("User with id %s does not exist", id));
        userRepository.deleteById(id);
    }

    /**
     * Returns the username associated with username
     * @param username Username of the user that its being search
     * @return User
     */
    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public User getUserById(UUID id) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    /**
     * Adds a new student, it checks if the email is already used by another user, if it is it throws an EmailExistException,
     * if not it saves the student into the database with the role of STUDENT and verified false
     * @param student Student Object to add, it must contain all the required fields to create a student
     * @return Student
     * @throws EmailExistException Email already exist
     */
    @Override
    public Student addStudent(Student student) throws EmailExistException{
        if(userRepository.existsByEmail(student.getEmail()))
            throw new EmailExistException("There already exist an account with that email. Please try a different one!");
        /*TODO Password Hashing*/
        student.setRole(Roles.STUDENT);
        student.setVerified(false);
        return studentRepository.save(student);
    }

    /**
     * Adds a new Professor, it checks if the email is already used by another user, if it is it throws an EmailExistException,
     * if not it saves the professor into the database with the role of PROFESSOR and verified false
     * @param professor Professor Object to add, it must contain all the required fields to create a professor
     * @return Professor
     * @throws EmailExistException Email already exist
     */
    @Override
    public Professor addProfessor(Professor professor) throws EmailExistException {
        if(userRepository.existsByEmail(professor.getEmail()))
            throw new EmailExistException("There already exist an account with that email. Please try a different one!");
        professor.setRole(Roles.PROFESSOR);
        professor.setVerified(false);
        return professorRepository.save(professor);
    }

    @Override
    public Admin addAdmin(Admin admin) {
        return null;
    }
}
