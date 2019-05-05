package br.com.wirecard.backendchallenge.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.wirecard.backendchallenge.domain.Buyer;

public interface BuyerRepository extends MongoRepository<Buyer, String> {

}