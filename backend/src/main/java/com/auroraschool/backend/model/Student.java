package com.auroraschool.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@PrimaryKeyJoinColumn(name = "user_id") // verweist auf einen User
public class Student extends User{
    @Column(unique = true, nullable = false)
    private Long enrollmentNUmber;

    //@OneToMany(cascade = CascadeType.ALL,mappedBy = "student", orphanRemoval = true)
    //private List<Classes> classes = new ArrayList<>();
}
