package com.example.demomvc.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDate;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @Column(unique = true, nullable = false, length = 40)
    private  String name;

    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private  String uuid;

    @Column(nullable = false)
    private  Double price;

    @Column(nullable = false)
    private Integer qty;

    private  LocalDate importedDate;
    private  Boolean status;


}
