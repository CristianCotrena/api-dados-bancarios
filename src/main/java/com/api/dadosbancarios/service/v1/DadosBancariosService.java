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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DadosBancariosService {

    private final DadosBancariosRepository dadosBancariosRepository;

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

        erros.addAll(verificarExistencia(dadosbancariosRequestDto));

        if (!erros.isEmpty()) {
            ResponseErrorBuilder resultado = new ResponseErrorBuilder(HttpStatus.BAD_REQUEST, erros);
            return resultado.get().getBody();
        }

        var dadosbancarios = new DadosBancariosTransforme().transformarParaDadosBancariosModel(dadosbancariosRequestDto);

        var savedDadosBancarios = dadosBancariosRepository.save(dadosbancarios);

        return new ResponseSuccessBuilder<>(HttpStatus.CREATED, savedDadosBancarios, "Dados bancários cadastrados com sucesso.").get().getBody();
    }

    private List<BaseErrorDto> verificarExistencia(DadosbancariosRequestDto dadosbancariosRequestDto) {
        List<BaseErrorDto> erros = new ArrayList<>();

        if (dadosBancariosRepository.existsById(dadosbancariosRequestDto.getIdFuncionario())) {
            erros.add(new BaseErrorDto("id.", MensagensDeErros.FIELD_ALREADY_REGISTERED));
        }
        if (dadosBancariosRepository.existsByNome(dadosbancariosRequestDto.getNome()).orElse(false)) {
            erros.add(new BaseErrorDto("nome.", MensagensDeErros.FIELD_ALREADY_REGISTERED));
        }
        if (dadosBancariosRepository.existsByBanco(dadosbancariosRequestDto.getBanco()).orElse(false)) {
            erros.add(new BaseErrorDto("banco.", MensagensDeErros.FIELD_ALREADY_REGISTERED));
        }
        if (dadosBancariosRepository.existsByAgencia(dadosbancariosRequestDto.getAgencia()).orElse(false)) {
            erros.add(new BaseErrorDto("agencia.", MensagensDeErros.FIELD_ALREADY_REGISTERED));
        }
        if (dadosBancariosRepository.existsByConta(dadosbancariosRequestDto.getConta()).orElse(false)) {
            erros.add(new BaseErrorDto("conta.", MensagensDeErros.FIELD_ALREADY_REGISTERED));
        }
        if (dadosBancariosRepository.existsByValidade(dadosbancariosRequestDto.getValidade()).orElse(false)) {
            erros.add(new BaseErrorDto("validade.", MensagensDeErros.FIELD_ALREADY_REGISTERED));
        }
        return erros;
    }
}