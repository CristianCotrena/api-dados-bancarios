package com.api.dadosbancarios.validation;

import com.api.dadosbancarios.base.dto.BaseErrorDto;
import com.api.dadosbancarios.constants.MensagensDeErros;
import com.api.dadosbancarios.dtos.DadosbancariosRequestDto;

import java.util.ArrayList;
import java.util.List;

public class DadosBancariosValidation {
    public List<BaseErrorDto> validate(DadosbancariosRequestDto dadosbancariosRequestDto) {
        List<BaseErrorDto> erros = validateCamposRequeridos(dadosbancariosRequestDto);
        return erros;
    }

    public List<BaseErrorDto> validateCamposRequeridos(DadosbancariosRequestDto dadosbancariosRequestDto) {
        List<BaseErrorDto> erros = new ArrayList<>();

        // Campos obrigatórios
        if (dadosbancariosRequestDto.getNome() == null) {
            erros.add(new BaseErrorDto("Nome", MensagensDeErros.EMPTY_FIELD));
        }
        if (dadosbancariosRequestDto.getIdFuncionario() == null) {
            erros.add(new BaseErrorDto("idFuncionario", MensagensDeErros.EMPTY_FIELD));
        }
        if (dadosbancariosRequestDto.getBanco() == null) {
            erros.add(new BaseErrorDto("Banco", MensagensDeErros.EMPTY_FIELD));
        }
        if (dadosbancariosRequestDto.getAgencia() == null) {
            erros.add(new BaseErrorDto("Agencia", MensagensDeErros.EMPTY_FIELD));
        }
        if (dadosbancariosRequestDto.getConta() == null) {
            erros.add(new BaseErrorDto("Conta", MensagensDeErros.EMPTY_FIELD));
        }
        if (dadosbancariosRequestDto.getValidade() == null) {
            erros.add(new BaseErrorDto("Validade", MensagensDeErros.EMPTY_FIELD));
        }
        return erros;
    }
}
