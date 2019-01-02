package br.com.lfuso.cursos.spring.restful.order.services;

import br.com.lfuso.cursos.spring.restful.order.domain.Cliente;
import br.com.lfuso.cursos.spring.restful.order.dto.ClienteDTO;
import br.com.lfuso.cursos.spring.restful.order.repositories.ClienteRepository;
import br.com.lfuso.cursos.spring.restful.order.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente find(Integer toFind) {
		return repository.findById(toFind)
				.orElseThrow(() -> new ObjectNotFoundException
						("Objeto " + Cliente.class.getSimpleName() + " não encontrado com id [ " + toFind + " ]"));
	}

	public List<Cliente> findAll() {
		return repository.findAll();
	}

	public Page<Cliente> findAll(Integer page, Integer itens, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, itens, Sort.Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		return repository.save(cliente);
	}

	public void update(Cliente cliente) {
		Cliente toUpdate = this.find(cliente.getId());
		updateData(cliente, toUpdate);
		repository.save(toUpdate);
	}

	public void delete(Integer id) {
		this.find(id);
		repository.deleteById(id);
	}

	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null, null);
	}

	private static void updateData(Cliente reference, Cliente toUpdate) {
		String nome = reference.getNome();
		String email = reference.getEmail();
		toUpdate.setEmail(email);
		toUpdate.setNome(nome);
	}
}
