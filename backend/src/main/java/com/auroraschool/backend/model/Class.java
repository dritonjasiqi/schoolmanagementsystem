package com.auroraschool.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "classes")
@Getter
@Setter
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name= "professor_id", nullable = false)
    private Professor professor;

    //@OneToMany(mappedBy = "class",cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<Enrollment> enrollments = new ArrayList<>();
}
