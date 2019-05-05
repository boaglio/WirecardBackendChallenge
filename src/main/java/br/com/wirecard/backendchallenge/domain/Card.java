package br.com.wirecard.backendchallenge.domain;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Card {

    @Id
    private String  id;
    private String  cardHolderName;
    private String  cardNumber;
    private String  cardExpirationDate;
    private Integer cardCVV;

}
