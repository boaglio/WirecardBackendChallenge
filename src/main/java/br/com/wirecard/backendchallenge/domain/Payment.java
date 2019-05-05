package br.com.wirecard.backendchallenge.domain;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.wirecard.backendchallenge.type.PaymentMethod;
import br.com.wirecard.backendchallenge.type.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "payment")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payment {

    @Id
    private String        id;
    @NotNull
    private Double        amount;
    @NotNull
    private PaymentMethod type;
    @NotNull
    private Client        client;
    private Buyer         buyer;
    private Card          card;
    private Boleto        boleto;
    private PaymentStatus status;

}
