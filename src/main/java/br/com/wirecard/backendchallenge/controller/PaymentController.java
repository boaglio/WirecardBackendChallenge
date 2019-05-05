package br.com.wirecard.backendchallenge.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.wirecard.backendchallenge.domain.Payment;
import br.com.wirecard.backendchallenge.exception.PaymentNotFoundException;
import br.com.wirecard.backendchallenge.service.PaymentService;
import lombok.extern.java.Log;

@RestController
@Log
public class PaymentController {

    private PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping(value = "/payment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Payment createPayment(@RequestBody @Valid Payment payment) {

        log.info("Entrada: " + payment);

        service.process(payment);

        return payment;
    }

    @GetMapping("/payment/status/{id}")
    public Payment createPayment(@PathVariable String id) throws PaymentNotFoundException {

        log.info("Status do pagamento: " + id);

        Optional<Payment> storedPayment = service.findById(id);

        Payment payment = null;
        if (storedPayment.isEmpty())
            throw new PaymentNotFoundException();
        else
            payment = storedPayment.get();

        return payment;
    }

}
