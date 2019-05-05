package br.com.wirecard.backendchallenge.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wirecard.backendchallenge.domain.Buyer;
import br.com.wirecard.backendchallenge.domain.Payment;
import br.com.wirecard.backendchallenge.repository.BuyerRepository;
import br.com.wirecard.backendchallenge.repository.PaymentRepository;

@RestController
@RequestMapping("/list/all")
public class ListDomainsController {

    private BuyerRepository   buyerRepository;
    private PaymentRepository paymentRepository;

    @Autowired
    public ListDomainsController(BuyerRepository buyerRepository, PaymentRepository paymentRepository) {
        this.buyerRepository = buyerRepository;
        this.paymentRepository = paymentRepository;
    }

    @GetMapping("/buyer")
    public List<Buyer> allBuyers() {
        return buyerRepository.findAll();
    }

    @GetMapping("/payment")
    public List<Payment> allPayments() {
        return paymentRepository.findAll();
    }

}
