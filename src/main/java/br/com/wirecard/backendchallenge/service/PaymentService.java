package br.com.wirecard.backendchallenge.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wirecard.backendchallenge.domain.Boleto;
import br.com.wirecard.backendchallenge.domain.Payment;
import br.com.wirecard.backendchallenge.repository.PaymentRepository;
import br.com.wirecard.backendchallenge.type.PaymentMethod;
import lombok.extern.java.Log;

@Service
@Log
public class PaymentService {

    private PaymentRepository repository;

    @Autowired
    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    public void process(Payment payment) {
        log.info("Processando pagamento: " + payment);

        if (payment.getType().equals(PaymentMethod.BOLETO)) {
            payment.setBoleto(Boleto.builder().id(generateRandom()).build());
        }

        repository.save(payment);
    }

    public Optional<Payment> findById(String id) {
        return repository.findById(id);
    }

    public Payment save(Payment payment) {
        return repository.save(payment);
    }

    private static Long generateRandom() {

        Random random = new Random();

        return random.nextLong() & 0xffffffffL;

    }

}
