package com.xandy.financaspessoais.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.xandy.financaspessoais.exception.RegraNegocioException;
import com.xandy.financaspessoais.model.entity.Usuario;
import com.xandy.financaspessoais.model.repository.UsuarioRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioServiceTest {

	@Autowired
	UsuarioService service;

	@Autowired
	UsuarioRepository repository;

	@Test
	public void deveValidarEmail() {
		// cenário
		repository.deleteAll();

		// ação
		service.validarEmail("alexandre@email.com");
	}

	@Test
	public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
		// cenário
		Usuario usuario = Usuario.builder().nome("alexandre").email("alexandre@email.com").build();
		repository.save(usuario);

		// ação
		Assertions.assertThrows(RegraNegocioException.class, 
				() -> service.validarEmail("alexandre@email.com"));
	}
}
