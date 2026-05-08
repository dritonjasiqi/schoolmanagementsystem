package com.auroraschool.backend.service;

import com.auroraschool.backend.model.*;
import com.auroraschool.backend.repostiory.ProfessorRepository;
import com.auroraschool.backend.repostiory.UserRepository;
import com.auroraschool.backend.repostiory.StudentRepository;
import com.auroraschool.backend.exception.EmailExistException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceClass implements UserService {
    private final UserRepository userRepository;
    private final ProfessorRepository professorRepository;
    private final StudentRepository studentRepository;


    @Override
    public void removeUser(UUID id) throws IllegalAccessError {
        if(!userRepository.existsById(id))
            throw new IllegalArgumentException(String.format("User with id %s does not exist", id));
        userRepository.deleteById(id);
    }

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

    @Override
    public Student addStudent(Student student) throws EmailExistException{
        if(userRepository.existsByEmail(student.getEmail()))
            throw new EmailExistException("There already exist an account with that email. Please try a different one!");
        /*TODO Password Hashing*/
        student.setRole(Roles.STUDENT);
        student.setVerified(false);
        return studentRepository.save(student);
    }

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
