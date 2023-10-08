package com.api.dadosbancarios.validation;

import com.api.dadosbancarios.base.dto.BaseErrorDto;
import com.api.dadosbancarios.constants.MensagensDeErros;
import com.api.dadosbancarios.dtos.DadosbancariosRequestDto;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DadosBancariosValidation {
    public List<BaseErrorDto> validate(DadosbancariosRequestDto dadosbancariosRequestDto) {
        List<BaseErrorDto> erros = validateCamposRequeridos(dadosbancariosRequestDto);
        return erros.size() > 0 ? erros : validateCamposInvalidos(dadosbancariosRequestDto, erros);
    }

    public List<BaseErrorDto> validateCamposRequeridos(DadosbancariosRequestDto dadosbancariosRequestDto) {
        List<BaseErrorDto> erros = new ArrayList<>();

        //Todo: Campos obrigatórios
        if (dadosbancariosRequestDto.getNome() == null) {
            erros.add(new BaseErrorDto("nome", MensagensDeErros.EMPTY_FIELD));
        }
        if (dadosbancariosRequestDto.getIdFuncionario() == null && dadosbancariosRequestDto.getIdFornecedor() == null) {
            erros.add(new BaseErrorDto("idFuncionario", MensagensDeErros.EMPTY_FIELD));
            erros.add(new BaseErrorDto("idFornecedor", MensagensDeErros.EMPTY_FIELD));
        }
        if (dadosbancariosRequestDto.getIdFuncionario() != null && dadosbancariosRequestDto.getIdFornecedor() != null) {
            erros.add(new BaseErrorDto("idFuncionario", MensagensDeErros.EMPTY_FIELD));
            erros.add(new BaseErrorDto("idFornecedor", MensagensDeErros.EMPTY_FIELD));
        }
        if (dadosbancariosRequestDto.getBanco() == null) {
            erros.add(new BaseErrorDto("banco", MensagensDeErros.EMPTY_FIELD));
        }
        if (dadosbancariosRequestDto.getAgencia() == null) {
            erros.add(new BaseErrorDto("agencia", MensagensDeErros.EMPTY_FIELD));
        }
        if (dadosbancariosRequestDto.getConta() == null) {
            erros.add(new BaseErrorDto("conta", MensagensDeErros.EMPTY_FIELD));
        }
        if (dadosbancariosRequestDto.getValidade() == null) {
            erros.add(new BaseErrorDto("validade", MensagensDeErros.EMPTY_FIELD));
        }
        return erros;
    }

    //Todo: Campos inválidos
    public List<BaseErrorDto> validateCamposInvalidos(DadosbancariosRequestDto dadosbancariosRequestDto, List<BaseErrorDto> erros) {
        if (dadosbancariosRequestDto.getValidade().isBefore(ZonedDateTime.now())) {
            erros.add(new BaseErrorDto("validade", MensagensDeErros.INVALID_FIELD));
        }
        return erros;
    }
}

