package br.com.lfuso.cursos.spring.restful.order.resources;

import br.com.lfuso.cursos.spring.restful.order.domain.Categoria;
import br.com.lfuso.cursos.spring.restful.order.dto.CategoriaDTO;
import br.com.lfuso.cursos.spring.restful.order.services.CategoriaService;
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
@RequestMapping(path = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {

		List<CategoriaDTO> categorias = categoriaService.findAll()
				.stream()
				.map(CategoriaDTO::ofCategoria)
				.collect(Collectors.toList());

		return ResponseEntity.ok(categorias);

	}

	@RequestMapping(path = {"/page"}, method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "itens", defaultValue = "24") Integer itens,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<CategoriaDTO> categorias = categoriaService.findAll(page, itens, orderBy, direction)
				.map(CategoriaDTO::ofCategoria);

		return ResponseEntity.ok(categorias);

	}

	@RequestMapping(path = {"/{id}", "{id}"}, method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {

		Categoria found = categoriaService.find(id);
		return ResponseEntity.ok(found);

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody CategoriaDTO categoriaDTO) {

		Categoria categoria = categoriaService.fromDTO(categoriaDTO);
		categoria = categoriaService.insert(categoria);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(categoria.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(path = {"/{id}", "{id}"}, method = RequestMethod.PUT)
	public ResponseEntity<Void> save(@PathVariable Integer id, @Valid @RequestBody CategoriaDTO categoriaDTO) {

		Categoria categoria = categoriaService.fromDTO(categoriaDTO);
		categoriaService.update(categoria);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(path = {"/{id}", "{id}"}, method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {

		categoriaService.delete(id);
		return ResponseEntity.noContent().build();

	}

}
