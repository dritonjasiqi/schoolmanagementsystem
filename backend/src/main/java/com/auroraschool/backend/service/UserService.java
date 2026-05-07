package com.auroraschool.backend.service;

import java.util.UUID;

import com.auroraschool.backend.model.Admin;
import com.auroraschool.backend.model.Professor;
import com.auroraschool.backend.model.Student;
import com.auroraschool.backend.model.User;

public interface UserService {
    void removeUser(UUID id);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    User getUserById(UUID id);
    User updateUser(User user);
    Student addStudent(Student student);
    Professor addProfessor(Professor professor);
    Admin addAdmin(Admin admin);
}
