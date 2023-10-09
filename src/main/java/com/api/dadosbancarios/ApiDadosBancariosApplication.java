package com.api.dadosbancarios;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@OpenAPIDefinition(info = @Info(title = "API Dados-bancarios", version = "1", description = "Microserviço dados-bancarios para API Forma NT - Academia"))
public class ApiDadosBancariosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiDadosBancariosApplication.class, args);
    }

    @GetMapping("/")
    public String helloWord() {
        return "Hello word da api-dados-bancarios";
    }
}