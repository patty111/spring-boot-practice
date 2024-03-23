package org.example.demo.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    private String name;
    private LocalDate birthday;
    private Integer age;
    private String email;
    private String school;
    private String major;
    private String org;

    public void setAge(Integer age) {
        this.age = calculateAge(birthday);
    }

    private Integer calculateAge(LocalDate birthday) {
        return LocalDate.now().getYear() - birthday.getYear();
    }

}
