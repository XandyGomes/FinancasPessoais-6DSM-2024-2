package com.xandy.financaspessoais.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xandy.financaspessoais.model.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>{

}
