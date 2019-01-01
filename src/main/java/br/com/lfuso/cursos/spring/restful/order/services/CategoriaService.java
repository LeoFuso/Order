package br.com.lfuso.cursos.spring.restful.order.services;

import br.com.lfuso.cursos.spring.restful.order.domain.Categoria;
import br.com.lfuso.cursos.spring.restful.order.dto.CategoriaDTO;
import br.com.lfuso.cursos.spring.restful.order.repositories.CategoriaRepository;
import br.com.lfuso.cursos.spring.restful.order.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria find(Integer toFind) {
		return repository.findById(toFind)
				.orElseThrow(() -> new ObjectNotFoundException
						("Objeto " + Categoria.class.getSimpleName() + " não encontrado com id [ " + toFind + " ]"));
	}

	public List<Categoria> findAll() {
		return repository.findAll();
	}

	public Page<Categoria> findAll(Integer page, Integer itens, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, itens, Sort.Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return repository.save(categoria);
	}

	public void update(Categoria categoria) {
		this.find(categoria.getId());
		repository.save(categoria);
	}

	public void delete(Integer id) {
		this.find(id);
		repository.deleteById(id);
	}

	public Categoria fromDTO(CategoriaDTO categoriaDTO) {
		return new Categoria(categoriaDTO.getId(), categoriaDTO.getNome());
	}
}
