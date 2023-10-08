package com.api.dadosbancarios.service_v1;

import com.api.dadosbancarios.base.dto.BaseDto;
import com.api.dadosbancarios.base.dto.BaseResultDto;
import com.api.dadosbancarios.dtos.DadosbancariosRequestDto;
import com.api.dadosbancarios.entity.model.DadosBancariosModel;
import com.api.dadosbancarios.repository.DadosBancariosRepository;
import com.api.dadosbancarios.service.v1.DadosBancariosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("DadosBancáriosService - Testes")
public class DadosBancariosServiceTest {
    @MockBean
    private DadosBancariosRepository dadosBancariosRepository;
    @Autowired
    private DadosBancariosService dadosBancariosService;
    private DadosbancariosRequestDto dadosbancariosRequestDto;
    private DadosBancariosModel dadosbancariosModel;

    @BeforeEach
    public void setUp() {
        dadosBancariosRepository = mock(DadosBancariosRepository.class);
        dadosBancariosService = new DadosBancariosService(dadosBancariosRepository);
        dadosbancariosModel = new DadosBancariosModel();
        dadosbancariosModel.setIdFuncionario(UUID.fromString("H17787ho"));
        dadosbancariosModel.setNome("Gabrielly");
        dadosbancariosModel.setBanco("Bradesco");
        dadosbancariosModel.setAgencia("001");
        dadosbancariosModel.setConta("123456789");
        dadosbancariosModel.setValidade(ZonedDateTime.now().parse("2025-01-01"));
        dadosbancariosModel.setStatus(1);
    }

    @Test
    @DisplayName("Teste Cadastrar Dados Bancários - Sucesso")
    public void testeCadastrarDadosBancarios_Sucesso() {
        UUID idFuncionario = UUID.randomUUID();

        when(dadosBancariosRepository.existsById(any(UUID.class))).thenReturn(false);
        when(dadosBancariosRepository.existsByNome(any(String.class))).thenReturn(Optional.of(false));

        when(dadosBancariosRepository.save(any(DadosBancariosModel.class))).thenReturn(dadosbancariosModel);

        BaseDto baseDto = dadosBancariosService.cadastrarDadosBancarios(dadosbancariosRequestDto);
        DadosbancariosRequestDto dadosbancariosRequestDto = new DadosbancariosRequestDto(dadosbancariosModel.getIdFuncionario().toString());
        BaseResultDto cadastradoComSucesso = new BaseResultDto(
                baseDto.getResultado().getStatus(), baseDto.getResultado().getDescricao());

        assertEquals(HttpStatus.CREATED.value(), baseDto.getResultado().getStatus());
        assertEquals("Cadastrado com sucesso.", baseDto.getResultado().getDescricao());
        assertEquals(HttpStatus.CREATED.value(), cadastradoComSucesso.getStatus());
        assertEquals(dadosbancariosModel.getId().toString(), dadosbancariosRequestDto.getIdFuncionario());
        assertEquals("Cadastrado com sucesso", cadastradoComSucesso.getDescricao());
    }

    @Test
    @DisplayName("Teste Cadastrar Dados Bancários - Sucesso com Status Zero")
    public void testeCadastrarDadosBancarios_Sucesso_StatusZero() {
        UUID idFuncionario = UUID.randomUUID();

        when(dadosBancariosRepository.existsById(any(UUID.class))).thenReturn(false);
        when(dadosBancariosRepository.existsByNome(any(String.class))).thenReturn(Optional.of(false));

        dadosbancariosModel.setStatus(0);

        when(dadosBancariosRepository.save(any(DadosBancariosModel.class))).thenReturn(dadosbancariosModel);

        BaseDto baseDto = dadosBancariosService.cadastrarDadosBancarios(dadosbancariosRequestDto);
        DadosbancariosRequestDto dadosbancariosRequestDto = new DadosbancariosRequestDto(dadosbancariosModel.getIdFuncionario().toString());
        BaseResultDto cadastradoComSucesso = new BaseResultDto(baseDto.getResultado().getStatus(), baseDto.getResultado().getDescricao());

        assertEquals(HttpStatus.CREATED.value(), baseDto.getResultado().getStatus());
        assertEquals("Cadastrado com sucesso.", baseDto.getResultado().getDescricao());
        assertEquals(HttpStatus.CREATED.value(), cadastradoComSucesso.getStatus());
        assertEquals(dadosbancariosModel.getId().toString(), dadosbancariosRequestDto.getIdFuncionario());
        assertEquals("Cadastrado com sucesso", cadastradoComSucesso.getDescricao());
    }
}