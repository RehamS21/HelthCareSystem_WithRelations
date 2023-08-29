package com.example.healthcaresystem_project4.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@RequiredArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "the room type must not null")
    @Column(columnDefinition = "varchar(30) not null, check( roomtype = 'emergency' or roomtype ='intensive care' or roomtype = 'operating' or roomtype = 'hypnosis')")
    private String roomtype;

    @NotNull(message = "patient id must not null")
    @Positive
    // must be unique becuase no two paitent have a same room
    @Column(columnDefinition = "int unique not null")
    private Integer patientId;
}
