package br.com.wirecard.backendchallenge.controller;

import java.time.LocalDateTime;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.moip.validators.CreditCard;
import br.com.wirecard.backendchallenge.domain.Buyer;
import br.com.wirecard.backendchallenge.domain.Payment;
import br.com.wirecard.backendchallenge.dto.CheckoutReturn;
import br.com.wirecard.backendchallenge.exception.BuyerNotFoundException;
import br.com.wirecard.backendchallenge.exception.InvalidCreditCardException;
import br.com.wirecard.backendchallenge.service.BuyerService;
import br.com.wirecard.backendchallenge.service.PaymentService;
import br.com.wirecard.backendchallenge.type.PaymentStatus;
import br.com.wirecard.backendchallenge.type.TransactionStatus;
import lombok.extern.java.Log;

@RestController
@Log
public class CheckoutController {

    private PaymentController paymentController;
    private PaymentService    paymentService;
    private BuyerService      buyerService;

    @Autowired
    public CheckoutController(PaymentController paymentController, PaymentService paymentService,
            BuyerService buyerService) {
        this.paymentController = paymentController;
        this.paymentService = paymentService;
        this.buyerService = buyerService;
    }

    @PostMapping(value = "/checkout", consumes = MediaType.APPLICATION_JSON_VALUE)
    public CheckoutReturn checkout(@RequestBody @Valid Payment payment)
            throws InvalidCreditCardException, BuyerNotFoundException {

        log.info("Entrada: " + payment);

        Optional<Buyer> storedBuyer = buyerService.findById(payment.getBuyer().getId());

        if (storedBuyer.isPresent()) {
            payment.setBuyer(storedBuyer.get());
        } else {
            throw new BuyerNotFoundException();
        }

        Payment validatedPayment = validateInput(payment);

        String paymentId = paymentController.createPayment(validatedPayment).getId();

        log.info("paymentId: " + paymentId);

        paymentService.save(validatedPayment);

        return CheckoutReturn.builder().date(LocalDateTime.now()).orderID(paymentId)
                .status(TransactionStatus.SUCCESSFUL).build();

    }

    private Payment validateInput(Payment payment) throws InvalidCreditCardException {
        log.info("Validando checkout: " + payment);

        switch (payment.getType()) {
            case CREDIT_CARD:
                CreditCard creditCard = null;
                if (payment.getCard() != null) {
                    creditCard = new CreditCard(payment.getCard().getCardNumber());
                }
                if (creditCard != null && creditCard.isValid()) {
                    payment.setStatus(PaymentStatus.APPROVED);
                    log.info("Bandeira: " + creditCard.getBrand());
                } else
                    throw new InvalidCreditCardException();
                break;

            default:
                break;
        }

        return payment;

    }
}
