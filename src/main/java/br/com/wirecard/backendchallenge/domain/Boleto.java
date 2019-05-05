package br.com.wirecard.backendchallenge.domain;

import org.springframework.data.annotation.Id;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Boleto {

    @Id
    private Long id;
}
