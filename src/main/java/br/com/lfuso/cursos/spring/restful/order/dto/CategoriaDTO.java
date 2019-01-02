package br.com.lfuso.cursos.spring.restful.order.dto;

import br.com.lfuso.cursos.spring.restful.order.domain.Categoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO implements Serializable {

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Size(min = 5, max = 80, message = "Nome deve conter entre [ 5; 80] caracteres")
	private String nome;

	public static CategoriaDTO of(Categoria categoria) {
		return new CategoriaDTO(categoria.getId(), categoria.getNome());
	}

}
