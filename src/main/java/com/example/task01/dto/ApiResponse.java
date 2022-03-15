package com.example.task01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sanjarbek Allayev, ср 16:46. 09.03.2022
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse {
private String message;
private boolean success;
private Object object;

    public ApiResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
