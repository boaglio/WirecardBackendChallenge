package br.com.wirecard.backendchallenge.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wirecard.backendchallenge.domain.Buyer;
import br.com.wirecard.backendchallenge.repository.BuyerRepository;

@Service
public class BuyerService {

    private BuyerRepository repository;

    @Autowired
    public BuyerService(BuyerRepository repository) {
        this.repository = repository;
    }

    public Optional<Buyer> findById(String id) {
        return repository.findById(id);
    }

}
