package com.api.dadosbancarios.service_v1;

import com.api.dadosbancarios.base.dto.BaseDto;
import com.api.dadosbancarios.base.dto.BaseErrorDto;
import com.api.dadosbancarios.dtos.DadosbancariosRequestDto;
import com.api.dadosbancarios.entity.model.DadosBancariosModel;
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

    @Test
    public void cadastrarDadosBancarios() {
        var request = new DadosbancariosRequestDto();// cria um objeto

        request.setIdFuncionario(fromString("5776b4fa-f29d-46b1-a4b7-caa0fb230ac5"));
        request.setNome("Gaby Venturini");
        request.setBanco("Bradesco");
        request.setAgencia("001");
        request.setConta("123456789");
        request.setValidade(ZonedDateTime.parse("2025-10-09T16:19:54.112+00:00"));
        request.setStatus(1);

        var resultado = dadosBancariosService.cadastrarDadosBancarios(request);

        assertEquals(CREATED.value(), resultado.getResultado().getStatus());//Isso sugere que o código espera que o método tenha sucesso
        assertEquals("Dados bancários cadastrados com sucesso.", resultado.getResultado().getDescricao());
    }

    @Test
    public void erroAoCadastrarIdFuncionarioExistente() {
        var request = new DadosbancariosRequestDto(); //Cria um objeto DadosbancariosRequestDto.


        request.setIdFuncionario(randomUUID());// define um ID de funcionário aleatório para o objeto request.
        //simula um cenário onde você está tentando cadastrar dados bancários para um funcionário com um ID que já existe no banco de dados.

        when(dadosBancariosRepository.existsById(request.getIdFuncionario())).thenReturn(true);//Configura o comportamento simulado do seu repositório (dadosBancariosRepository)
        // Isso simula o cenário em que o ID do funcionário já existe no banco de dados.


        var resultado = dadosBancariosService.cadastrarDadosBancarios(request);//chama o método cadastrarDadosBancarios da classe DadosBancariosService, passando o objeto request como argumento.
        // Este método deve verificar se o ID do funcionário já existe no banco de dados e, com base nessa verificação, retornar um resultado apropriado.


        assertEquals(BAD_REQUEST.value(), resultado.getResultado().getStatus());//está usando a biblioteca de testes para verificar se o resultado retornado pelo método cadastrarDadosBancarios é um código HTTP 400 (BAD_REQUEST).
        // Espera-se que, quando o ID do funcionário já existe, o método retorne um status de erro.
    }

    @Test
    public void testarErroAoCadastrarNomeExistente() {
        var request = new DadosbancariosRequestDto();

        request.setNome("Gaby Venturini");

        when(dadosBancariosRepository.existsByNome(request.getNome())).thenReturn(Optional.of(true));

        var result = dadosBancariosService.cadastrarDadosBancarios(request);

        assertEquals(BAD_REQUEST.value(), result.getResultado().getStatus());
    }

    @Test
    public void testarErroAoCadastrarContaExistente() {
        var request = new DadosbancariosRequestDto();

        request.setConta("123456789");

        when(dadosBancariosRepository.existsByConta(request.getConta())).thenReturn(Optional.of(true));

        var result = dadosBancariosService.cadastrarDadosBancarios(request);

        assertEquals(BAD_REQUEST.value(), result.getResultado().getStatus());
    }

    @Test
    public void testarErroAoCadastrarValidadeExistente() {
        var request = new DadosbancariosRequestDto();

        request.setValidade(ZonedDateTime.parse("2025-10-09T16:19:54.112+00:00"));

        when(dadosBancariosRepository.existsByValidade(request.getValidade())).thenReturn(Optional.of(true));

        var result = dadosBancariosService.cadastrarDadosBancarios(request);

        assertEquals(BAD_REQUEST.value(), result.getResultado().getStatus());
    }

    @Test
    public void testarErroDeValidacao() {
        var request = new DadosbancariosRequestDto();

        request.setIdFuncionario(fromString("5776b4fa-f29d-46b1-a4b7-caa0fb230ac5"));
        request.setNome("Gaby Venturini");
        request.setBanco("Bradesco");
        request.setAgencia("001");
        request.setConta("123456789");
        request.setValidade(now().parse("2025-10-09T16:19:54.112+00:00"));
        request.setStatus(1);

        List<BaseErrorDto> validationErrors = new ArrayList<>();
        validationErrors.add(new BaseErrorDto("campo", "mensagem de erro"));

        when(dadosBancariosRepository.existsByValidade(request.getValidade())).thenReturn(Optional.of(true));
        when(dadosBancariosValidation.validate(request)).thenReturn(validationErrors);

        var result = dadosBancariosService.cadastrarDadosBancarios(request);

        assertEquals(BAD_REQUEST.value(), result.getResultado().getStatus());
    }

}