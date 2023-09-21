package com.api.dadosbancarios.repository;

import com.api.dadosbancarios.model.DadosbancariosMoldel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface DadosBancariosRepository extends JpaRepository<DadosbancariosMoldel, UUID> {
}
