package com.xandy.financaspessoais.model.repository;

import org.junit.jupiter.api.Test;

import java.util.Optional;

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
	public void devePersistirUmUsuarioNaBaseDeDados() {
		//cenário
		Usuario usuario = criarUsuario();
		
		//ação
		Usuario usuarioSalvo = repository.save(usuario);
		
		//verificacao
		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
	}
	
	@Test
	public void deveBuscarUmUsuarioPorEmail() {
		//cenário
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);
		
		//ação/execução
		Optional<Usuario> result = repository
				.findByEmail("alexandre@email.com");
		
		//verificacao
		Assertions.assertThat(result.isPresent()).isTrue();
	}
	
	@Test
	public void deveRetornarVazioAoBuscarUsuarioPorEmailQuandoNaoExisteNaBase() {
		//ação/execução
		Optional<Usuario> result = repository
				.findByEmail("alexandre@email.com");
		
		//verificacao
		Assertions.assertThat(result.isPresent()).isFalse();
	}

	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
		// cenário
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);

		// ação/ execução
		boolean result = repository.existsByEmail("alexandre@email.com");

		// verificacao
		Assertions.assertThat(result).isTrue();

	}
	
	@Test
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {
		// cenário

		// ação
		boolean result = repository.existsByEmail("alexandre@email.com");

		// verificacao
		Assertions.assertThat(result).isFalse();
	}

	public static Usuario criarUsuario() {
		return Usuario
				.builder()
				.nome("alexandre")
				.email("alexandre@email.com")
				.senha("1234")
				.build();
	}
}
