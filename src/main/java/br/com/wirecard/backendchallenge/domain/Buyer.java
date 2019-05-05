package br.com.wirecard.backendchallenge.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "buyer")
public class Buyer {

    @Id
    private String id;

    private String name;

    private String email;

    private String cpf;

}
