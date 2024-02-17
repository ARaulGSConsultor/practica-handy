package com.handy.practica.client;

import com.handy.practica.client.dto.SaleOrderDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;

@Component
@RequiredArgsConstructor
public class HandyClient {

    private final RestTemplate restTemplate;

    @Value("${handy.order.url}")
    private String urlhandy;

    @Value("${handy.token}")
    private String token;

    public List<SaleOrderDTO> getOrders(final LocalDateTime start, final LocalDateTime end) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Bearer " + token);

        return Optional.ofNullable(restTemplate.exchange(
                        UriComponentsBuilder.fromHttpUrl(urlhandy)
                                .queryParam("start", start.toString())
                                .queryParam("end", end.toString())
                                .build().encode().toUri(),
                        HttpMethod.GET,
                        new HttpEntity<>(httpHeaders),
                        new ParameterizedTypeReference<List<SaleOrderDTO>>() {
                        }))
                .map(ResponseEntity::getBody)
                .orElse(emptyList());
    }
}
