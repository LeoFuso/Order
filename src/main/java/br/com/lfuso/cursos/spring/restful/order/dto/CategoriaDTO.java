package br.com.lfuso.cursos.spring.restful.order.dto;

import br.com.lfuso.cursos.spring.restful.order.domain.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO implements Serializable {

	private Integer id;
	private String nome;

	public static CategoriaDTO ofCategoria(Categoria categoria) {
		return new CategoriaDTO(categoria.getId(), categoria.getNome());
	}
}
