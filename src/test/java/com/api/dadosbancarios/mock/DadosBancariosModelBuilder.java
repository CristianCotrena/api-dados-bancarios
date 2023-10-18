package com.api.dadosbancarios.mock;

import com.api.dadosbancarios.entity.model.DadosBancariosModel;

import java.time.ZonedDateTime;
import java.util.UUID;

public class DadosBancariosModelBuilder {
    public static DadosBancariosModel build(){
        DadosBancariosModel resultado = new DadosBancariosModel();
        resultado.setId(UUID.randomUUID());
        resultado.setNome("gabby");
        resultado.setBanco("Bradesco");
        resultado.setAgencia("0001");
        resultado.setConta("254569");
        resultado.setValidade(ZonedDateTime.parse("2025-10-20"));
        return resultado;
    }
}
