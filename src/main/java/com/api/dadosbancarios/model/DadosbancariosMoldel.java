package com.api.dadosbancarios.model;

import jakarta.persistence.*;
import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
@Entity
@Table(name = "dadosBancarios")
public class DadosbancariosMoldel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private UUID id;
    @Column(nullable = false, unique = true)
    private UUID id_funcionario;
    @Column(nullable = false, unique = true)
    private UUID id_fornecedor;
    @Column(nullable = false, unique = true)
    private String agencia;
    @Column(nullable = false, unique = true)
    private String banco;
    @Column(nullable = false, unique = true)
    private String conta;
    @Column(nullable = false, unique = true)
    private String nome;
    @Column(nullable = false)
    private Date validade;
    @Column(nullable = false, columnDefinition = "int default 1")
    private int status;

    public DadosbancariosMoldel(UUID id, UUID id_funcionario, UUID id_fornecedor, String agencia, String banco, String conta, String nome, Date validade, int status) {
        this.id = id;
        this.id_funcionario = id_funcionario;
        this.id_fornecedor = id_fornecedor;
        this.agencia = agencia;
        this.banco = banco;
        this.conta = conta;
        this.nome = nome;
        this.validade = validade;
        this.status = status;
    }

    public DadosbancariosMoldel() {
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(UUID id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public UUID getId_fornecedor() {
        return id_fornecedor;
    }

    public void setId_fornecedor(UUID id_fornecedor) {
        this.id_fornecedor = id_fornecedor;
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

    public Date getValidade() {
        return validade;
    }

    public void setValidade(Date validade) {
        this.validade = validade;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
