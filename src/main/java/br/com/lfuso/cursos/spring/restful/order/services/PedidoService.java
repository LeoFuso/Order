package br.com.lfuso.cursos.spring.restful.order.services;

import br.com.lfuso.cursos.spring.restful.order.domain.Pedido;
import br.com.lfuso.cursos.spring.restful.order.repositories.PedidoRepository;
import br.com.lfuso.cursos.spring.restful.order.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public Pedido buscar(Integer toFind) {
		return repository.findById(toFind)
				.orElseThrow(() -> new ObjectNotFoundException
						("Objeto " + Pedido.class.getSimpleName() + " não encontrado com id [ " + toFind + " ]"));
	}

	public List<Pedido> listar() {
		return repository.findAll();
	}
}
