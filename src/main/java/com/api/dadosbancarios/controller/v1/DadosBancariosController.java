package com.api.dadosbancarios.controller.v1;

import com.api.dadosbancarios.base.dto.BaseDto;
import com.api.dadosbancarios.dtos.DadosbancariosRequestDto;
import com.api.dadosbancarios.service.v1.DadosBancariosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/dadosbancarios")
@Tag(
        name = "API Dados-bancarios",
        description = "Microserviço dados-bancarios para API Forma NT - Academia"
)
public class DadosBancariosController {
    @Autowired
    private DadosBancariosService dadosBancariosService;

    @Operation(
            summary = "Criar dados bancários",
            description = "Cria um novo dado bancário",
            method = "POST"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Dados bancários criado com sucesso")
    @ApiResponse(
            responseCode = "400",
            description = "Erro de validação")
    @ApiResponse(
            responseCode = "500",
            description = "Erro interno")

    @PostMapping("/cadastrar")
    public BaseDto cadastrarDadosBancarios(@RequestBody DadosbancariosRequestDto dadosbancariosRequestDto) {
        return dadosBancariosService.cadastrarDadosBancarios(dadosbancariosRequestDto);
    }
}
