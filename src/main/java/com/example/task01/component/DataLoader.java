package com.example.task01.component;

import com.example.task01.entity.Address;
import com.example.task01.entity.Company;
import com.example.task01.entity.Department;
import com.example.task01.entity.Worker;
import com.example.task01.repository.AddressRepository;
import com.example.task01.repository.CompanyRepository;
import com.example.task01.repository.DepartmentRepository;
import com.example.task01.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.persistence.Column;

/**
 * @author Sanjarbek Allayev, ср 15:26. 09.03.2022
 */
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    @Value("${spring.sql.init.mode}")
    private String mode;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    final WorkerRepository workerRepository;
    final AddressRepository addressRepository;
    final CompanyRepository companyRepository;
    final DepartmentRepository departmentRepository;


    @Override
    public void run(String... args) throws Exception {
        if (mode.equals("always") && ddl.equals("create")){
            Address address= new Address();
            address.setHomeNumber("40A");
            address.setStreet("Mustaqillik");

            Address savedAddress = addressRepository.save(address);

            Company company=new Company();
            company.setAddress(savedAddress);
            company.setCorpName("Abc-Trading");
            company.setDirectorName("Sanjar");
            Company savedCompany = companyRepository.save(company);

            Department department= new Department();
            department.setCompany(savedCompany);
            department.setName("Marketing");
            Department savedDepartment = departmentRepository.save(department);

            Worker worker= new Worker();
            worker.setName("Sardor");
            Address address1= new Address();
            address1.setStreet("Zargarlik");
            address1.setHomeNumber("11A");
            Address savedAddress2 = addressRepository.save(address1);
            worker.setAddress(savedAddress2);
            worker.setPhoneNumber("+998900046722");
            worker.setDepartment(savedDepartment);
            Worker save = workerRepository.save(worker);
        }
    }
}
