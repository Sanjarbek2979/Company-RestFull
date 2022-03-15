package com.example.task01.service;

import com.example.task01.dto.ApiResponse;
import com.example.task01.dto.DepartmentDTO;
import com.example.task01.entity.Company;
import com.example.task01.entity.Department;
import com.example.task01.repository.CompanyRepository;
import com.example.task01.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Sanjarbek Allayev, ср 16:21. 09.03.2022
 */
@Service
@RequiredArgsConstructor
public class DepartmentService {
    final DepartmentRepository departmentRepository;
    final CompanyRepository companyRepository;


    public List<Department> getAll() {
        List<Department> all = departmentRepository.findAll();
        return all;
    }

    public ApiResponse getOne(Integer id) {
        Optional<Department> byId = departmentRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("Xatolik",false);
        }
        Department department = byId.get();
        return new ApiResponse("Mana",true,department);
    }

    public ApiResponse save(DepartmentDTO dto) {
        Department department = new Department();
        department.setName(dto.getName());
        Optional<Company> byId = companyRepository.findById(dto.getCompanyId());
        if (!byId.isPresent()){
            return new ApiResponse("Xatolik",false);
        }
        Company company = byId.get();
        department.setCompany(company);
        Department save = departmentRepository.save(department);
        return new ApiResponse("Added",true,save);
    }

    public ApiResponse edit(Integer id, DepartmentDTO dto) {
        Optional<Department> byId = departmentRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("Xatolik",false);
        }
        Department department = byId.get();
        department.setName(dto.getName());
        Optional<Company> byId1 = companyRepository.findById(dto.getCompanyId());
        if (!byId1.isPresent()){
            return new ApiResponse("Xatolik",false);
        }
        Company company = byId1.get();
        department.setCompany(company);
        Department edit = departmentRepository.save(department);
        return new ApiResponse("Edited",true,edit);
    }

    public ApiResponse delete(Integer id) {
        departmentRepository.deleteById(id);
        return new ApiResponse("Deleted",true);
    }
}
