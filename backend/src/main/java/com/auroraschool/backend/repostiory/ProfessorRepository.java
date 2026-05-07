package com.auroraschool.backend.repostiory;

import com.auroraschool.backend.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor,UUID> {
    Optional<Professor> findByEmail(String email);
}
