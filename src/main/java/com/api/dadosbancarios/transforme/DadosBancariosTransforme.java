package com.api.dadosbancarios.transforme;

import com.api.dadosbancarios.dtos.DadosbancariosRequestDto;
import com.api.dadosbancarios.entity.model.DadosBancariosModel;

public class DadosBancariosTransforme {

    public DadosBancariosModel transformarParaDadosBancariosModel(DadosbancariosRequestDto dto) {
        DadosBancariosModel dadosbancariosModel = new DadosBancariosModel();
        dadosbancariosModel.setIdFuncionario(dto.getIdFuncionario());
        dadosbancariosModel.setIdFornecedor(dto.getIdFornecedor());
        dadosbancariosModel.setNome(dto.getNome());
        dadosbancariosModel.setBanco(dto.getBanco());
        dadosbancariosModel.setAgencia(dto.getAgencia());
        dadosbancariosModel.setConta(dto.getConta());
        dadosbancariosModel.setValidade(dto.getValidade());

        if (dto.getStatus() != null) {
            dadosbancariosModel.setStatus(dto.getStatus());
        } else {
            dadosbancariosModel.setStatus(1);
        }
        return dadosbancariosModel;
    }
}
