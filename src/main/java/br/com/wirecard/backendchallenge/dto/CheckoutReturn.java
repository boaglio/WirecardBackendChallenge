package br.com.wirecard.backendchallenge.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.wirecard.backendchallenge.type.TransactionStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CheckoutReturn {

    private TransactionStatus status;

    private LocalDateTime date;

    private String orderID;
}
