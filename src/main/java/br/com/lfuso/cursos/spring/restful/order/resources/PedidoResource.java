package br.com.lfuso.cursos.spring.restful.order.resources;

import br.com.lfuso.cursos.spring.restful.order.domain.Pedido;
import br.com.lfuso.cursos.spring.restful.order.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/pedidos")
public class PedidoResource {

	@Autowired
	private PedidoService PedidoService;

	@RequestMapping(path = {"/", ""}, method = RequestMethod.GET)
	public ResponseEntity<List<Pedido>> listar() {

		return ResponseEntity.ok(PedidoService.listar());

	}

	@RequestMapping(path = {"/{id}", "{id}"}, method = RequestMethod.GET)
	public ResponseEntity<Pedido> find(@PathVariable Integer id) {

		Pedido found = PedidoService.buscar(id);
		return ResponseEntity.ok(found);

	}

}
