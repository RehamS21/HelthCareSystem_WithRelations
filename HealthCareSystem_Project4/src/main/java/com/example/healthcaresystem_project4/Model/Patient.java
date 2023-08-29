package com.example.healthcaresystem_project4.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@RequiredArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "patient name must not null")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotEmpty(message = "patient phone number must not null")
    @Pattern(regexp = "^(009665|9665|\\+9665|05|5)(5|0|3|6|4|9|1|8|7)([0-9]{7})$")
    @Column(columnDefinition = "varchar(10) unique not null")
    private String phone;

    @NotNull(message = "age must not null")
    @Positive(message = "age must be positive")
    @Column(columnDefinition = "int(80) not null")
    private Integer age;

    @NotNull(message = "patient money must not null")
    @Positive(message = "patient salary must be a positive")
    @Column(columnDefinition = "int not null")
    private Integer money;

    @Column(columnDefinition = "BOOLEAN default false")
    private Boolean appointment = false;

    @NotNull(message = "Doctor id must not be null")
    @Positive
    @Column(columnDefinition = "int  not null")
    private Integer doctorid;


}
