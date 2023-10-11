package com.api.dadosbancarios.entity.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "dadosBancarios")
public class DadosBancariosModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id;
    @Column(unique = true)
    private UUID idFuncionario;
    @Column(unique = true)
    private UUID idFornecedor;
    @Column(nullable = false)
    private String agencia;
    @Column(nullable = false)
    private String banco;
    @Column(nullable = false, unique = true)
    private String conta;
    @Column(nullable = false, unique = true)
    private String nome;
    @Column(nullable = false)
    private ZonedDateTime validade;
    @Column(nullable = false, columnDefinition = "int default 1")
    private Integer status;

    public DadosBancariosModel(UUID id, UUID idFuncionario, UUID idFornecedor, String agencia, String banco, String conta, String nome, ZonedDateTime validade, Integer status) {
        this.id = id;
        this.idFuncionario = idFuncionario;
        this.idFornecedor = idFornecedor;
        this.agencia = agencia;
        this.banco = banco;
        this.conta = conta;
        this.nome = nome;
        this.validade = validade;
        this.status = status;
    }

    public DadosBancariosModel() {

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

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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