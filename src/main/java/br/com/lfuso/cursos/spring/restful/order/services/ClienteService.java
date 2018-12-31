package br.com.lfuso.cursos.spring.restful.order.services;

import br.com.lfuso.cursos.spring.restful.order.domain.Cliente;
import br.com.lfuso.cursos.spring.restful.order.repositories.ClienteRepository;
import br.com.lfuso.cursos.spring.restful.order.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente buscar(Integer toFind) {
		return repository.findById(toFind)
				.orElseThrow(() -> new ObjectNotFoundException
						("Objeto " + Cliente.class.getSimpleName() + " não encontrado com id [ " + toFind + " ]"));
	}

	public List<Cliente> listar() {
		return repository.findAll();
	}
}
