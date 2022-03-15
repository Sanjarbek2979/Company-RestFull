package com.example.task01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sanjarbek Allayev, ср 16:25. 09.03.2022
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentDTO {
    private String name;
    private Integer companyId;
}
