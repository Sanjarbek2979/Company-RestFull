package com.example.task01.controller;

import com.example.task01.dto.ApiResponse;
import com.example.task01.dto.WorkerDTO;
import com.example.task01.entity.Company;
import com.example.task01.entity.Worker;
import com.example.task01.service.CompanyService;
import com.example.task01.service.WorkerService;
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
@RequestMapping("/api/worker")
@RequiredArgsConstructor
public class WorkerController {
    final WorkerService workerService;

    @GetMapping
    public HttpEntity<?> getAll(){
        List<Worker> all = workerService.getAll();
        return ResponseEntity.ok().body(new ApiResponse("List of companies",true,all));
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        ApiResponse one = workerService.getOne(id);

        return ResponseEntity.status(one.isSuccess()? HttpStatus.FOUND:HttpStatus.NO_CONTENT).body(one);
    }
    @PostMapping
    public HttpEntity<?> save(@Valid @RequestBody WorkerDTO dto){
        ApiResponse save = workerService.save(dto);
        return ResponseEntity.status(save.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(save);
    }
    @PutMapping("/{id}")
    public HttpEntity<?> edit(@Valid @RequestBody WorkerDTO dto,@PathVariable Integer id){
        ApiResponse edit = workerService.edit(id,dto);
        return ResponseEntity.status(edit.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(edit);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id){
        ApiResponse delete = workerService.delete(id);
        return ResponseEntity.status(delete.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(delete);
    }
}
