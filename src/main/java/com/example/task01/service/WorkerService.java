package com.example.task01.service;

import com.example.task01.dto.ApiResponse;
import com.example.task01.dto.WorkerDTO;
import com.example.task01.entity.Address;
import com.example.task01.entity.Department;
import com.example.task01.entity.Worker;
import com.example.task01.repository.AddressRepository;
import com.example.task01.repository.DepartmentRepository;
import com.example.task01.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Sanjarbek Allayev, ср 16:21. 09.03.2022
 */
@Service
@RequiredArgsConstructor
public class WorkerService {
    final WorkerRepository workerRepository;
    final AddressRepository addressRepository;
    final DepartmentRepository departmentRepository;


    public List<Worker> getAll() {
        List<Worker> all = workerRepository.findAll();
        return all;
    }

    public ApiResponse getOne(Integer id) {
        Optional<Worker> byId = workerRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("Xatolik",false);
        }
        Worker worker = byId.get();
        return new ApiResponse("Mana",true,worker);
    }

    public ApiResponse save(WorkerDTO dto) {
        Worker worker = new Worker();
        worker.setPhoneNumber(dto.getPhoneNumber());
        worker.setName(dto.getName());
        Address address= new Address();
        address.setHomeNumber(dto.getHomeNumber());
        address.setStreet(dto.getStreet());
        Address save = addressRepository.save(address);
        worker.setAddress(save);
        Optional<Department> byId = departmentRepository.findById(dto.getDepartmentId());
        if (!byId.isPresent()){
            return new ApiResponse("Xatolik",false);
        }
        Department department = byId.get();
        worker.setDepartment(department);
        Worker save1 = workerRepository.save(worker);
        return new ApiResponse("Added",true,save1);
    }

    public ApiResponse edit(Integer id, WorkerDTO dto) {
        Optional<Worker> byId = workerRepository.findById(id);
        if (!byId.isPresent()){
            return new ApiResponse("Xatolik",false);
        }
        Worker worker = byId.get();
        Optional<Address> byId1 = addressRepository.findById(worker.getAddress().getId());
        if (!byId1.isPresent()){
            return new ApiResponse("Xatolik",false);
        }
        Address address = byId1.get();
        address.setStreet(dto.getStreet());
        address.setHomeNumber(dto.getHomeNumber());
        Address save = addressRepository.save(address);
        worker.setAddress(save);
        worker.setPhoneNumber(dto.getPhoneNumber());
        worker.setName(dto.getName());
        Optional<Department> byId2 = departmentRepository.findById(dto.getDepartmentId());
        if (!byId2.isPresent()){
            return new ApiResponse("Xatolik",false);
        }
        Department department = byId2.get();
        worker.setDepartment(department);
        Worker edit = workerRepository.save(worker);
        return new ApiResponse("Edited",true,edit);
    }

    public ApiResponse delete(Integer id) {
        workerRepository.deleteById(id);
        return new ApiResponse("Deleted",true);
    }
}
