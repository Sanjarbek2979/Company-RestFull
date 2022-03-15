package com.example.task01.controller;

import com.example.task01.dto.ApiResponse;
import com.example.task01.dto.CompanyDTO;
import com.example.task01.entity.Company;
import com.example.task01.service.CompanyService;
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
@RequestMapping("/api/company")
@RequiredArgsConstructor
public class CompanyController {
  final CompanyService companyService;

  @GetMapping
  public HttpEntity<?> getAll(){
      List<Company> all = companyService.getAll();
  return ResponseEntity.ok().body(new ApiResponse("List of companies",true,all));
  }
  @GetMapping("/{id}")
  public HttpEntity<?> getOne(@PathVariable Integer id){
    ApiResponse one = companyService.getOne(id);

    return ResponseEntity.status(one.isSuccess()? HttpStatus.FOUND:HttpStatus.NO_CONTENT).body(one);
  }
  @PostMapping
  public HttpEntity<?> save(@Valid @RequestBody CompanyDTO dto){
    ApiResponse save = companyService.save(dto);
    return ResponseEntity.status(save.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(save);
  }
  @PutMapping("/{id}")
  public HttpEntity<?> edit(@Valid @RequestBody CompanyDTO dto,@PathVariable Integer id){
    ApiResponse edit = companyService.edit(id,dto);
    return ResponseEntity.status(edit.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(edit);
  }
  @DeleteMapping("/{id}")
  public HttpEntity<?> delete(@PathVariable Integer id){
    ApiResponse delete = companyService.delete(id);
    return ResponseEntity.status(delete.isSuccess()? HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(delete);
  }

}
