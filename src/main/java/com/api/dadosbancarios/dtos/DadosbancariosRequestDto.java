package com.api.dadosbancarios.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.UUID;

public class DadosbancariosRequestDto {
    @Schema(description = "Id do funcionário", example = "546546556")
    private UUID idFuncionario;
    @Schema(description = "Id do fornecedor", example = "546546556")
    private UUID idFornecedor;
    @Schema(description = "Nome do cliente", example = "Marcos")
    private String nome;
    @Schema(description = "Banco", example = "Bradesco")
    private String banco;
    @Schema(description = "Agencia", example = "0001")
    private String agencia;
    @Schema(description = "Número da conta", example = "165165654")
    private String conta;
    @Schema(description = "Data de validade do cartao", example = "20/02")
    private LocalDate validade;
    @Schema(description = "Status da conta", example = "1")
    private Integer status;

    public DadosbancariosRequestDto(String nome) {
        this.idFuncionario = idFuncionario;
        this.idFornecedor = idFornecedor;
        this.nome = nome;
        this.banco = banco;
        this.agencia = agencia;
        this.conta = conta;
        this.validade = validade;
        this.status = status;
    }
@JsonCreator
public DadosbancariosRequestDto() {
    }

    public UUID getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(UUID idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public UUID getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(UUID idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}