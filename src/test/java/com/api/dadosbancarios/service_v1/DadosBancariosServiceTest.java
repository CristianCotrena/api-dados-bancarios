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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@DisplayName("DadosBancáriosService - Testes")
public class DadosBancariosServiceTest {
    @InjectMocks
    private DadosBancariosService dadosBancariosService;

    @Mock
    private DadosBancariosRepository dadosBancariosRepository;

    @BeforeEach
    public void setUp() {
        dadosBancariosRepository = mock(DadosBancariosRepository.class);
        dadosBancariosService = new DadosBancariosService(dadosBancariosRepository);

        DadosbancariosRequestDto dtoDadosBancarios = new DadosbancariosRequestDto();
        dtoDadosBancarios.setIdFuncionario(UUID.fromString("5776b4fa-f29d-46b1-a4b7-caa0fb230ac5"));
        dtoDadosBancarios.setNome("Gabrielly");
        dtoDadosBancarios.setBanco("Bradesco");
        dtoDadosBancarios.setAgencia("001");
        dtoDadosBancarios.setConta("123456789");
        dtoDadosBancarios.setValidade(ZonedDateTime.now().parse("2025-10-09T16:19:54.112+00:00"));
        dtoDadosBancarios.setStatus(1);

        DadosBancariosModel dadosBancarios = new DadosBancariosModel();
    }

    @Test
    public void testCadastrarDadosBancarios_IdJaExiste() {
        DadosbancariosRequestDto dadosBancarios = new DadosbancariosRequestDto();

        when(dadosBancariosRepository.existsById(dadosBancarios.getIdFuncionario())).thenReturn(true);

        BaseDto result = dadosBancariosService.cadastrarDadosBancarios(dadosBancarios);

        assertEquals(HttpStatus.BAD_REQUEST, result.getResultado().getStatus());
    }


    @Test
    public void testCadastrarDadosBancarios_NomeJaExiste() {
        DadosbancariosRequestDto dadosBancarios = new DadosbancariosRequestDto();

        when(dadosBancariosRepository.existsByNome(dadosBancarios.getNome())).thenReturn(Optional.of(true));

        BaseDto result = dadosBancariosService.cadastrarDadosBancarios(dadosBancarios);

        assertEquals(HttpStatus.BAD_REQUEST, result.getResultado().getStatus());
    }

    @Test
    public void testCadastrarDadosBancarios_ContaJaExiste() {
        DadosbancariosRequestDto dadosBancarios = new DadosbancariosRequestDto();

        when(dadosBancariosRepository.existsByConta(dadosBancarios.getConta())).thenReturn(Optional.of(true));

        BaseDto result = dadosBancariosService.cadastrarDadosBancarios(dadosBancarios);

        assertEquals(HttpStatus.BAD_REQUEST, result.getResultado().getStatus());
    }

    @Test
    public void testCadastrarDadosBancarios_ValidadeJaExiste() {
        DadosbancariosRequestDto dadosBancarios = new DadosbancariosRequestDto();

        when(dadosBancariosRepository.existsByValidade(dadosBancarios.getValidade())).thenReturn(Optional.of(true));

        BaseDto result = dadosBancariosService.cadastrarDadosBancarios(dadosBancarios);

        assertEquals(HttpStatus.BAD_REQUEST, result.getResultado().getStatus());
    }

    @Test
    public void testCadastrarDadosBancarios_Sucesso() {
        DadosbancariosRequestDto requestDto = new DadosbancariosRequestDto();

        when(dadosBancariosRepository.existsById(requestDto.getIdFuncionario())).thenReturn(false);
        when(dadosBancariosRepository.existsByNome(requestDto.getNome())).thenReturn(Optional.of(false));
        when(dadosBancariosRepository.existsByConta(requestDto.getConta())).thenReturn(Optional.of(false));
        when(dadosBancariosRepository.existsByValidade(requestDto.getValidade())).thenReturn(Optional.of(false));
        when(dadosBancariosRepository.save(any(DadosBancariosModel.class))).thenReturn(new DadosBancariosModel());

        BaseDto result = dadosBancariosService.cadastrarDadosBancarios(requestDto);

        assertEquals(HttpStatus.CREATED, result.getResultado().getStatus());
    }

    @Test
    public void testCadastrarDadosBancarios_ErroValidacao() {
        DadosbancariosRequestDto requestDto = new DadosbancariosRequestDto();
        List<BaseErrorDto> validationErrors = new ArrayList<>();
        validationErrors.add(new BaseErrorDto("campo", "mensagem de erro"));

        when(new DadosBancariosValidation().validate(requestDto)).thenReturn(validationErrors);

        BaseDto result = dadosBancariosService.cadastrarDadosBancarios(requestDto);

        assertEquals(HttpStatus.BAD_REQUEST, result.getResultado().getStatus());
    }
}