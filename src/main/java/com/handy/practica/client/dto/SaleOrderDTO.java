package com.handy.practica.client.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Accessors
public class SaleOrderDTO implements Serializable {

    private Long id;

    private Double totalSales;

}