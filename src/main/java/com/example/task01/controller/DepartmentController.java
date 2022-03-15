package com.example.task01.controller;

import com.example.task01.dto.ApiResponse;
import com.example.task01.dto.DepartmentDTO;
import com.example.task01.entity.Department;
import com.example.task01.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Sanjarbek Allayev, ср 16:27. 09.03.2022
 */
@RestController
@RequestMapping("/api/department")
@RequiredArgsConstructor
public class DepartmentController {
    final DepartmentService departmentService;

    @GetMapping
    public HttpEntity<?> getAll(){
        List<Department> all = departmentService.getAll();
        return ResponseEntity.ok().body(new ApiResponse("List of departments",true,all));
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        ApiResponse one = departmentService.getOne(id);

        return ResponseEntity.status(one.isSuccess()? HttpStatus.FOUND:HttpStatus.NO_CONTENT).body(one);
    }
    @PostMapping
    public HttpEntity<?> save(@Valid @RequestBody DepartmentDTO dto){
        ApiResponse save = departmentService.save(dto);
        return ResponseEntity.status(save.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(save);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@Valid @RequestBody DepartmentDTO dto,@PathVariable Integer id){
        ApiResponse edit = departmentService.edit(id,dto);
        return ResponseEntity.status(edit.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(edit);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id){
        ApiResponse delete = departmentService.delete(id);
        return ResponseEntity.status(delete.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(delete);
    }
}
