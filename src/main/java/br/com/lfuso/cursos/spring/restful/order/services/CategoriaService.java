package br.com.lfuso.cursos.spring.restful.order.services;

import br.com.lfuso.cursos.spring.restful.order.domain.Categoria;
import br.com.lfuso.cursos.spring.restful.order.repositories.CategoriaRepository;
import br.com.lfuso.cursos.spring.restful.order.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria buscar(Integer toFind) {
		return repository.findById(toFind)
				.orElseThrow(() -> new ObjectNotFoundException
						("Objeto " + Categoria.class.getSimpleName() + " não encontrado com id [ " + toFind + " ]"));
	}

	public List<Categoria> listar() {
		return repository.findAll();
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return repository.save(categoria);
	}
}
