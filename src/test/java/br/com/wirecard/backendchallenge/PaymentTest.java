package br.com.wirecard.backendchallenge;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.wirecard.backendchallenge.domain.Client;
import br.com.wirecard.backendchallenge.domain.Payment;
import br.com.wirecard.backendchallenge.type.PaymentMethod;
import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
public class PaymentTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc      mvc;
    private ObjectMapper mapper;

    @Before
    public void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
        this.mapper = new ObjectMapper();
    }

    @Test
    public void pagamentoTipoInvalido() throws Exception {

        String URL = "/payment";

        Payment paymentInvalido = Payment.builder().client(Client.builder().id(1l).build()).amount(344.0).build();
        String input = mapper.writeValueAsString(paymentInvalido);

        log.info(" payment JSON invalido: " + input);
        System.out.println(this.mvc.perform(post(URL)).andDo(print()));

        this.mvc.perform(post(URL).content(input).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void pagamentoTipoBoleto() throws Exception {

        String URL = "/payment";

        Payment paymentBoleto = Payment.builder().client(Client.builder().id(1l).build()).amount(344.0)
                .type(PaymentMethod.BOLETO).build();
        String input = mapper.writeValueAsString(paymentBoleto);

        log.info(" payment JSON boleto: " + input);
        System.out.println(this.mvc.perform(post(URL)).andDo(print()));

        this.mvc.perform(post(URL).content(input).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().string(containsString("BOLETO")));
    }

    @Test
    public void pagamentoTipoCreditCard() throws Exception {

        String URL = "/payment";

        Payment paymentBoleto = Payment.builder().client(Client.builder().id(1l).build()).amount(344.0)
                .type(PaymentMethod.CREDIT_CARD).build();
        String input = mapper.writeValueAsString(paymentBoleto);

        log.info(" payment JSON boleto: " + input);
        System.out.println(this.mvc.perform(post(URL)).andDo(print()));

        this.mvc.perform(post(URL).content(input).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(content().string(containsString("CARD")));
    }
}
