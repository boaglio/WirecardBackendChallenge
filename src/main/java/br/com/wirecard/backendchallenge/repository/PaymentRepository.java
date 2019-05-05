package br.com.wirecard.backendchallenge.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.wirecard.backendchallenge.domain.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String> {

}
