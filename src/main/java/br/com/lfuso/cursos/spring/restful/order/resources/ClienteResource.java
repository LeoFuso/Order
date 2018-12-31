package br.com.lfuso.cursos.spring.restful.order.resources;

import br.com.lfuso.cursos.spring.restful.order.domain.Cliente;
import br.com.lfuso.cursos.spring.restful.order.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(path = {"/", ""}, method = RequestMethod.GET)
	public ResponseEntity<List<Cliente>> listar() {

		return ResponseEntity.ok(clienteService.listar());

	}

	@RequestMapping(path = {"/{id}", "{id}"}, method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {

		Cliente found = clienteService.buscar(id);
		return ResponseEntity.ok(found);

	}

}
