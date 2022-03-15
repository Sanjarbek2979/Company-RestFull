package com.example.task01.repository;

import com.example.task01.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sanjarbek Allayev, ср 16:05. 09.03.2022
 */
public interface AddressRepository extends JpaRepository<Address,Integer> {

}
