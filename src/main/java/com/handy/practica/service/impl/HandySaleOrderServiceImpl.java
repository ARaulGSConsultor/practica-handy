package com.handy.practica.service.impl;

import com.handy.practica.client.HandyClient;
import com.handy.practica.client.dto.SaleOrderDTO;
import com.handy.practica.repository.SaleOrderRepository;
import com.handy.practica.repository.model.SaleOrder;
import com.handy.practica.service.HandySaleOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HandySaleOrderServiceImpl implements HandySaleOrderService {

    private final HandyClient handyClient;

    private final SaleOrderRepository saleOrderRepository;

    @Override
    public List<SaleOrderDTO> get() {

        final LocalDateTime start = saleOrderRepository.findFirstByOrderByRegisterDateDesc()
                .map(SaleOrder::getRegisterDate)
                .orElse(LocalDateTime.now());

        final LocalDateTime end = LocalDateTime.now();

        List<SaleOrderDTO> orders = handyClient.getOrders(start, end);

        List<SaleOrder> saleOrdersToSave = orders.stream()
                .map(saleOrderDTO -> {
                    final var saleOrder = new SaleOrder();
                    saleOrder.setTotalSales(saleOrder.getTotalSales());
                    saleOrder.setId(saleOrder.getId());
                    saleOrder.setRegisterDate(LocalDateTime.now());

                    return saleOrder;
                })
                .collect(Collectors.toList());

        saleOrderRepository.saveAll(saleOrdersToSave);

        return orders;
    }
}
