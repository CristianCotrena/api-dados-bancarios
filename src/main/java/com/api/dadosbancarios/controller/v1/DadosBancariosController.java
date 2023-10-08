package com.api.dadosbancarios.controller.v1;

import com.api.dadosbancarios.base.dto.BaseDto;
import com.api.dadosbancarios.dtos.DadosbancariosRequestDto;
import com.api.dadosbancarios.service.v1.DadosBancariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/dadosbancarios")
public class DadosBancariosController {
    @Autowired
    private DadosBancariosService dadosBancariosService;

    @PostMapping("/cadastrar")
    public BaseDto cadastrarDadosBancarios(@RequestBody DadosbancariosRequestDto dadosbancariosRequestDto) {
        return dadosBancariosService.cadastrarDadosBancarios(dadosbancariosRequestDto);
    }
}
