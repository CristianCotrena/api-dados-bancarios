package com.api.dadosbancarios.service_v1;

import com.api.dadosbancarios.base.dto.BaseDto;
import com.api.dadosbancarios.base.dto.BaseErrorDto;
import com.api.dadosbancarios.dtos.DadosbancariosRequestDto;
import com.api.dadosbancarios.entity.model.DadosBancariosModel;
import com.api.dadosbancarios.mock.DadosBancariosModelBuilder;
import com.api.dadosbancarios.mock.DadosBancariosRequestDtoBuilder;
import com.api.dadosbancarios.repository.DadosBancariosRepository;
import com.api.dadosbancarios.service.v1.DadosBancariosService;
import com.api.dadosbancarios.validation.DadosBancariosValidation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.time.ZonedDateTime.now;
import static java.util.UUID.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@DisplayName("DadosBancáriosService - Testes")
@ExtendWith(MockitoExtension.class)
public class DadosBancariosServiceTest {

    DadosBancariosRepository dadosBancariosRepository = mock(DadosBancariosRepository.class);
    DadosBancariosValidation dadosBancariosValidation = mock(DadosBancariosValidation.class);
    DadosBancariosService dadosBancariosService = new DadosBancariosService(dadosBancariosRepository);

    @DisplayName("01 - Cadastrar Dados Bancários")
    @Test
    public void cadastrarDadosBancarios() {
        var request = DadosBancariosRequestDtoBuilder.build();

        var response = DadosBancariosModelBuilder.build();

        when(dadosBancariosRepository.save(any(DadosBancariosModel.class))).thenReturn(response);

        BaseDto<DadosBancariosModel> resultado = dadosBancariosService.cadastrarDadosBancarios(request);

        assertEquals(response.getId(), resultado.getDados().getId());
        assertEquals(CREATED.value(), resultado.getResultado().getStatus());
        assertEquals("Dados bancários cadastrados com sucesso.", resultado.getResultado().getDescricao());
    }

    @DisplayName("02 - Erro ao cadastrar id Funcionário existente")
    @Test
    public void erroAoCadastrarIdFuncionarioExistente() {
        var request = DadosBancariosRequestDtoBuilder.build();

        request.setIdFuncionario(randomUUID());

        when(dadosBancariosRepository.existsById(request.getIdFuncionario())).thenReturn(true);

        var resultado = dadosBancariosService.cadastrarDadosBancarios(request);

        assertEquals(BAD_REQUEST.value(), resultado.getResultado().getStatus());
    }

    @DisplayName("03 - Erro ao cadastrar id Fornecedor existente")
    @Test
    public void erroAoCadastrarIdFornecedorExistente() {
        var request = DadosBancariosRequestDtoBuilder.build();

        request.setIdFornecedor(randomUUID());

        when(dadosBancariosRepository.existsById(request.getIdFornecedor())).thenReturn(true);

        var resultado = dadosBancariosService.cadastrarDadosBancarios(request);

        assertEquals(BAD_REQUEST.value(), resultado.getResultado().getStatus());
    }

    @DisplayName("04 - Erro ao cadastrar nome existente")
    @Test
    public void testarErroAoCadastrarNomeExistente() {
        var request = DadosBancariosRequestDtoBuilder.build();

        request.setNome("Gaby Venturini");

        when(dadosBancariosRepository.existsByNome(request.getNome())).thenReturn(Optional.of(true));

        var result = dadosBancariosService.cadastrarDadosBancarios(request);

        assertEquals(BAD_REQUEST.value(), result.getResultado().getStatus());
    }

    @DisplayName("05 - Erro ao cadastrar conta existente")
    @Test
    public void testarErroAoCadastrarContaExistente() {
        var request = DadosBancariosRequestDtoBuilder.build();

        request.setConta("123456789");

        when(dadosBancariosRepository.existsByConta(request.getConta())).thenReturn(Optional.of(true));

        var result = dadosBancariosService.cadastrarDadosBancarios(request);

        assertEquals(BAD_REQUEST.value(), result.getResultado().getStatus());
    }

    @DisplayName("06 - Erro ao cadastrar validade existente")
    @Test
    public void testarErroAoCadastrarValidadeExistente() {
        var request = DadosBancariosRequestDtoBuilder.build();

        request.setValidade(ZonedDateTime.parse("2025-10-09T16:19:54.112+00:00"));

        when(dadosBancariosRepository.existsByValidade(request.getValidade())).thenReturn(Optional.of(true));

        var result = dadosBancariosService.cadastrarDadosBancarios(request);

        assertEquals(BAD_REQUEST.value(), result.getResultado().getStatus());
    }

    @DisplayName("07 - Erro na Validação")
    @Test
    public void testarErroDeValidacao() {
        var request = DadosBancariosRequestDtoBuilder.build();

        request.setIdFuncionario(fromString("5776b4fa-f29d-46b1-a4b7-caa0fb230ac5"));
        request.setNome("Gaby Venturini");
        request.setBanco("Bradesco");
        request.setAgencia("001");
        request.setConta("123456789");
        request.setValidade(ZonedDateTime.parse("2025-10-09T16:19:54.112+00:00"));
        request.setStatus(1);

        List<BaseErrorDto> validationErrors = new ArrayList<>();
        validationErrors.add(new BaseErrorDto("campo", "mensagem de erro"));

        when(dadosBancariosRepository.existsByValidade(request.getValidade())).thenReturn(Optional.of(true));
        when(dadosBancariosValidation.validate(request)).thenReturn(validationErrors);

        var result = dadosBancariosService.cadastrarDadosBancarios(request);

        assertEquals(BAD_REQUEST.value(), result.getResultado().getStatus());
    }

}