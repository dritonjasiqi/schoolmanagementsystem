package com.auroraschool.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "professors")
@PrimaryKeyJoinColumn(name = "user_id")
@Getter
@Setter
public class  Professor extends User{
    private String personalEmail;
    private String cvUrl;

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private List<Enrollment> courses;
}
