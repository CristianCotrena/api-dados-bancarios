package com.api.dadosbancarios.service.v1;

import com.api.dadosbancarios.base.dto.BaseDto;
import com.api.dadosbancarios.base.dto.BaseErrorDto;
import com.api.dadosbancarios.builder.ResponseErrorBuilder;
import com.api.dadosbancarios.builder.ResponseSuccessBuilder;
import com.api.dadosbancarios.constants.MensagensDeErros;
import com.api.dadosbancarios.dtos.DadosBancariosResponseDto;
import com.api.dadosbancarios.dtos.DadosbancariosRequestDto;
import com.api.dadosbancarios.entity.model.DadosBancariosModel;
import com.api.dadosbancarios.repository.DadosBancariosRepository;
import com.api.dadosbancarios.transforme.DadosBancariosTransforme;
import com.api.dadosbancarios.validation.DadosBancariosValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DadosBancariosService {
    private DadosBancariosRepository dadosBancariosRepository;

    @Autowired
    public DadosBancariosService(DadosBancariosRepository dadosBancariosRepository) {
        this.dadosBancariosRepository = dadosBancariosRepository;
    }

    @Transactional
    public BaseDto cadastrarDadosBancarios(DadosbancariosRequestDto dadosbancariosRequestDto) {
        var erros = new DadosBancariosValidation().validate(dadosbancariosRequestDto);

        if (!erros.isEmpty()) {
            ResponseErrorBuilder resultado = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, erros);
            return resultado.get().getBody();
        }
        if (dadosBancariosRepository.existsById(dadosbancariosRequestDto.getIdFuncionario())) {
            erros.add(new BaseErrorDto("Id.", MensagensDeErros.DADO_JA_CADASTRADO));
        }
        if (dadosBancariosRepository.existsByNome(dadosbancariosRequestDto.getNome()).orElse(false)) {
            erros.add(new BaseErrorDto("Nome.", MensagensDeErros.DADO_JA_CADASTRADO));
        }
        if (dadosBancariosRepository.existsByBanco(dadosbancariosRequestDto.getBanco()).orElse(false)) {
            erros.add(new BaseErrorDto("Banco.", MensagensDeErros.DADO_JA_CADASTRADO));
        }
        if (dadosBancariosRepository.existsByAgencia(dadosbancariosRequestDto.getAgencia()).orElse(false)) {
            erros.add(new BaseErrorDto("Agencia.", MensagensDeErros.DADO_JA_CADASTRADO));
        }
        if (dadosBancariosRepository.existsByConta(dadosbancariosRequestDto.getConta()).orElse(false)) {
            erros.add(new BaseErrorDto("Conta.", MensagensDeErros.DADO_JA_CADASTRADO));
        }
        if (dadosBancariosRepository.existsByValidade(dadosbancariosRequestDto.getValidade()).orElse(false)) {
            erros.add(new BaseErrorDto("Validade.", MensagensDeErros.DADO_JA_CADASTRADO));
        }

        var dadosbancarios = new DadosBancariosTransforme().transformarParaDadosBancariosModel(dadosbancariosRequestDto);

        var savedDadosBancarios = dadosBancariosRepository.save(dadosbancarios);

        return new ResponseSuccessBuilder<>(
                HttpStatus.CREATED, savedDadosBancarios,
                "Dados bancários cadastrados com sucesso."
        ).get().getBody();
    }
}