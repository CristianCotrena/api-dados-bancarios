package com.api.dadosbancarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApiDadosBancariosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiDadosBancariosApplication.class, args);
    }

    @GetMapping("/")
    public String helloWord() {
        return "Hello word da api-dados-bancarios";
    }
}
