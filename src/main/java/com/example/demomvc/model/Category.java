package com.example.demomvc.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.lang.reflect.GenericArrayType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="categories")
public class Category {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(unique = true, nullable = false, length = 40)
    String name;

    @Column( columnDefinition = "TEXT")
    String description;

}
