package com.api.dadosbancarios.mock;

import com.api.dadosbancarios.dtos.DadosbancariosRequestDto;

import java.time.ZonedDateTime;

import static java.util.UUID.fromString;

public class DadosBancariosRequestDtoBuilder {
    public static DadosbancariosRequestDto build(){
        var request = new DadosbancariosRequestDto();

        request.setIdFuncionario(fromString("5776b4fa-f29d-46b1-a4b7-caa0fb230ac5"));
        request.setNome("Gaby Venturini");
        request.setBanco("Bradesco");
        request.setAgencia("001");
        request.setConta("123456789");
        request.setValidade(ZonedDateTime.parse("2025-10-09T16:19:54.112+00:00"));
        request.setStatus(1);
        return request;
    }
}
