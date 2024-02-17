package com.handy.practica.repository.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class SaleOrder {

    @Id
    private Long id;

    private Double totalSales;

    private LocalDateTime registerDate;
}