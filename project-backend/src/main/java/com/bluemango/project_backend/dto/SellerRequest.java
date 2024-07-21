package com.bluemango.project_backend.dto;

import com.bluemango.project_backend.models.Seller;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SellerRequest {

    @Size(min = 3 , max = 255, message = "Name length min = 3 and max = 255")
    @NotNull(message = "Name cannot be null")
    @NotEmpty(message = "Name cannot be empty")
    @NotBlank(message = "Name cannot be blank")
    private String name; 


    @Min(value = 0, message = "Salary cannot be negative")
    private Double salary; 


    private Double bonus; 

    @NotNull(message = "Gender cannot be null")
    @Min(value = 0, message = "Gender must be 0 for Male and 1 for Female")
    @Max(value = 1, message = "Gender must be 0 for Male and 1 for Female")
    private Integer gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Double getBonus() {
        return bonus;
    }

    public void setBonus(Double bonus) {
        this.bonus = bonus;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Seller toEntity() {
        // TODO Auto-generated method stub
        Seller seller = new Seller();
        seller.setName(this.name);
        seller.setSalary(this.salary);
        seller.setBonus(this.bonus);
        seller.setGender(this.gender);
        return seller;
        
    } 

    



    
}