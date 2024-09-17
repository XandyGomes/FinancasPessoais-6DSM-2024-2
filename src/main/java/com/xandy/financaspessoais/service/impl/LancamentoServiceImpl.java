package com.xandy.financaspessoais.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xandy.financaspessoais.model.entity.Lancamento;
import com.xandy.financaspessoais.model.enums.StatusLancamento;
import com.xandy.financaspessoais.model.repository.LancamentoRepository;
import com.xandy.financaspessoais.service.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService {

	private LancamentoRepository repository;

	public LancamentoServiceImpl(LancamentoRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	@Transactional
	public Lancamento salvar(Lancamento lancamento) {
		return repository.save(lancamento);
	}

	@Override
	@Transactional
	public Lancamento atualizar(Lancamento lancamento) {
		Objects.requireNonNull(lancamento.getId());
		return repository.save(lancamento);
	}

	@Override
	@Transactional
	public void deletar(Lancamento lancamento) {
		Objects.requireNonNull(lancamento.getId());
		repository.delete(lancamento);

	}

	@Override
	@Transactional(readOnly = true) // apenas leitura
	public List<Lancamento> buscar(Lancamento lancamentoFiltro) {
		Example example = Example.of(lancamentoFiltro,
				ExampleMatcher.matching() // configuração para filtro
				.withIgnoreCase() // ignorar se é maiúsculo ou minúsculo
				.withStringMatcher(StringMatcher.CONTAINING) 
				// procurar que contenha apenas alguma parte que eu digitei
		);
		return repository.findAll(example);
	}

	@Override
	@Transactional
	public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {
		lancamento.setStatus(status);
		atualizar(lancamento);
	}
}
