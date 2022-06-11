package com.plantillabackend;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.plantillabackend.models.entity.TblUsuario;
import com.plantillabackend.models.repository.UsuarioRepository;

@SpringBootTest
class PlantillaBackendApplicationTests {

	@Autowired
	private UsuarioRepository repo;

	@Autowired
	private BCryptPasswordEncoder bcrypt;

	@Test
	void contextLoads() {
	}

	@Test
	void verficarClave() {
		long demo = 1;

		TblUsuario us = new TblUsuario();
		us.setIdUsuario(demo);
		us.setUsername("juanvalerio785@gmail.com");
		us.setPassword(bcrypt.encode("123"));
		us.setEnabled(true);

		TblUsuario retorno = repo.save(us);
		assertTrue(retorno.getPassword().equals(us.getPassword()));
	}

}
