package com.example.task01.service;

import com.example.task01.dto.ApiResponse;
import com.example.task01.dto.CompanyDTO;
import com.example.task01.entity.Address;
import com.example.task01.entity.Company;
import com.example.task01.repository.AddressRepository;
import com.example.task01.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Sanjarbek Allayev, ср 16:21. 09.03.2022
 */
@Service
@RequiredArgsConstructor
public class CompanyService {
    final CompanyRepository companyRepository;
    final AddressRepository addressRepository;


    public List<Company> getAll() {
        List<Company> all = companyRepository.findAll();
    return all;
    }

    public ApiResponse getOne(Integer id) {
        Optional<Company> byId = companyRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("Xatolik",false);
        }
        Company company = byId.get();
        return new ApiResponse("Mana",true,company);
    }

    public ApiResponse save(CompanyDTO dto) {
    Company company = new Company();
    company.setDirectorName(dto.getDirectorName());
    company.setCorpName(dto.getCorpName());
        Address address= new Address();
        address.setHomeNumber(dto.getHomeNumber());
        address.setStreet(dto.getStreet());
        Address save = addressRepository.save(address);
        company.setAddress(save);
        Company save1 = companyRepository.save(company);
        return new ApiResponse("Added",true,save1);
    }

    public ApiResponse edit(Integer id, CompanyDTO dto) {
        Optional<Company> byId = companyRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("Xatolik",false);
        }
        Company company = byId.get();
        Optional<Address> byId1 = addressRepository.findById(company.getAddress().getId());
        if (!byId1.isPresent()){
            return new ApiResponse("Xatolik",false);
        }
        Address address = byId1.get();
        address.setStreet(dto.getStreet());
        address.setHomeNumber(dto.getHomeNumber());
        Address save = addressRepository.save(address);
        company.setAddress(save);
        company.setDirectorName(dto.getDirectorName());
        company.setCorpName(dto.getCorpName());
        Company edit = companyRepository.save(company);
        return new ApiResponse("Edited",true,edit);
    }

    public ApiResponse delete(Integer id) {
        companyRepository.deleteById(id);
        return new ApiResponse("Deleted",true);
    }
}
