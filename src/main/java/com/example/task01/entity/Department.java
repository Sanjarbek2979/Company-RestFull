package com.example.task01.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * @author Sanjarbek Allayev, ср 15:27. 09.03.2022
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false)
    private String name;

    @ManyToOne
    Company company;

    @OneToMany(mappedBy ="department" ,cascade = CascadeType.REMOVE)
    List<Worker> workers;
}
