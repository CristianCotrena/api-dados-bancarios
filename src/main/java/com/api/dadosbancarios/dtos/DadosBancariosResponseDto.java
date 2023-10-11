package com.api.dadosbancarios.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.UUID;

public class DadosBancariosResponseDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private UUID id;
    private UUID idFuncionario;
    private UUID idFornecedor;
    private String nome;
    private String banco;
    private String agencia;
    private String conta;
    private ZonedDateTime validade;
    private Integer status;

    public DadosBancariosResponseDto(UUID id, UUID idFuncionario, UUID idFornecedor, String nome, String banco, String agencia, String conta, ZonedDateTime validade, Integer status) {
        this.id = id;
        this.idFuncionario = idFuncionario;
        this.idFornecedor = idFornecedor;
        this.nome = nome;
        this.banco = banco;
        this.agencia = agencia;
        this.conta = conta;
        this.validade = validade;
        this.status = status;
    }

    public DadosBancariosResponseDto() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public ZonedDateTime getValidade() {
        return validade;
    }

    public void setValidade(ZonedDateTime validade) {
        this.validade = validade;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
