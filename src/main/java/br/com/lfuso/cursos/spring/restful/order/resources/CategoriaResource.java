package br.com.lfuso.cursos.spring.restful.order.resources;

import br.com.lfuso.cursos.spring.restful.order.domain.Categoria;
import br.com.lfuso.cursos.spring.restful.order.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Categoria>> listar() {

		return ResponseEntity.ok(categoriaService.findAll());

	}

	@RequestMapping(path = {"/{id}", "{id}"}, method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {

		Categoria found = categoriaService.find(id);
		return ResponseEntity.ok(found);

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody Categoria categoria) {

		categoria = categoriaService.insert(categoria);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(categoria.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(path = {"/{id}", "{id}"}, method = RequestMethod.PUT)
	public ResponseEntity<Void> save(@PathVariable Integer id, @RequestBody Categoria categoria) {

		categoria.setId(id);
		categoriaService.update(categoria);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(path = {"/{id}", "{id}"}, method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {

		categoriaService.delete(id);
		return ResponseEntity.noContent().build();

	}

}
