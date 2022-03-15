package com.example.task01.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.Constraint;

import javax.persistence.*;

/**
 * @author Sanjarbek Allayev, ср 15:27. 09.03.2022
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "street", "homeNumber"})})
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String homeNumber;
}
