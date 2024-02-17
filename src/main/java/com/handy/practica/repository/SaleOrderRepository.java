package com.handy.practica.repository;

import com.handy.practica.repository.model.SaleOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SaleOrderRepository extends JpaRepository<SaleOrder, Long> {

    Optional<SaleOrder> findFirstByOrderByRegisterDateDesc();
}
