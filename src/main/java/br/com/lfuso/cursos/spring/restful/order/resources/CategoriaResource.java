package br.com.lfuso.cursos.spring.restful.order.resources;

import br.com.lfuso.cursos.spring.restful.order.domain.Categoria;
import br.com.lfuso.cursos.spring.restful.order.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping(path = {"/", ""}, method = RequestMethod.GET)
	public ResponseEntity<List<Categoria>> listar() {

		return ResponseEntity.ok(categoriaService.listar());

	}

	@RequestMapping(path = {"/{id}", "{id}"}, method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {

		Categoria found = categoriaService.buscar(id);
		return ResponseEntity.ok(found);

	}

}
