package com.xandy.financaspessoais.model.repository;

import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.xandy.financaspessoais.model.entity.Usuario;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {
	
	@Autowired
	UsuarioRepository repository;
	
	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
		//cenário
		Usuario usuario = Usuario
				.builder()
				.nome("alexandre")
				.email("alexandre@email.com")
				.senha("1234")
				.build();
		repository.save(usuario);
		
		//ação/ execução
		boolean result = repository.existsByEmail("alexandre@email.com");
		
		//verificacao
		Assertions.assertThat(result).isTrue();
		
	}
	
}

