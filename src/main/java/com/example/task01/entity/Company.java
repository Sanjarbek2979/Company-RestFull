package com.example.task01.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Sanjarbek Allayev, ср 15:27. 09.03.2022
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(nullable = false)
    private String corpName;
    @Column(nullable = false)
    private String directorName;

    @OneToOne
    Address address;
}
