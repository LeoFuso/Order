package br.com.lfuso.cursos.spring.restful.order.resources;

import br.com.lfuso.cursos.spring.restful.order.domain.Cliente;
import br.com.lfuso.cursos.spring.restful.order.dto.ClienteDTO;
import br.com.lfuso.cursos.spring.restful.order.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {

		List<ClienteDTO> clientes = clienteService.findAll()
				.stream()
				.map(ClienteDTO::of)
				.collect(Collectors.toList());

		return ResponseEntity.ok(clientes);

	}

	@RequestMapping(path = {"/page"}, method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "itens", defaultValue = "24") Integer itens,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<ClienteDTO> clientes = clienteService.findAll(page, itens, orderBy, direction)
				.map(ClienteDTO::of);

		return ResponseEntity.ok(clientes);

	}

	@RequestMapping(path = {"/{id}", "{id}"}, method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {

		Cliente found = clienteService.find(id);
		return ResponseEntity.ok(found);

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody ClienteDTO clienteDTO) {

		Cliente cliente = clienteService.fromDTO(clienteDTO);
		cliente = clienteService.insert(cliente);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(cliente.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(path = {"/{id}", "{id}"}, method = RequestMethod.PUT)
	public ResponseEntity<Void> save(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDTO) {

		Cliente cliente = clienteService.fromDTO(clienteDTO);
		cliente.setId(id);
		clienteService.update(cliente);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(path = {"/{id}", "{id}"}, method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {

		clienteService.delete(id);
		return ResponseEntity.noContent().build();

	}

}
