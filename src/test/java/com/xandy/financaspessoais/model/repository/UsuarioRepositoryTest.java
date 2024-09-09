package com.xandy.financaspessoais.model.repository;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.xandy.financaspessoais.model.entity.Usuario;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTest {
	
	@Autowired
	UsuarioRepository repository;
	
	@Autowired
	TestEntityManager entityManager;
	
	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
		//cenário
		Usuario usuario = Usuario
				.builder()
				.nome("alexandre")
				.email("alexandre@email.com")
				.senha("1234")
				.build();
		entityManager.persist(usuario);
		
		//ação/ execução
		boolean result = repository.existsByEmail("alexandre@email.com");
		
		//verificacao
		Assertions.assertThat(result).isTrue();
		
	}
	
	@Test
	public void deveRetornarFalsoQuandoNaoHouverusuarioCadastradoComOEmail() {
		//cenário
	
		//ação
		boolean result = repository.existsByEmail("alexandre@email.com");
		
		//verificacao
		Assertions.assertThat(result).isFalse();
	}
}

