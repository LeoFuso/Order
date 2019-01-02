package br.com.lfuso.cursos.spring.restful.order.dto;

import br.com.lfuso.cursos.spring.restful.order.domain.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO implements Serializable {

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatório")
	@Size(min = 5, max = 120, message = "Nome deve conter entre [ 5; 120] caracteres")
	private String nome;

	@NotEmpty
	@Email(message = "E-mail não é válido")
	private String email;

	public static ClienteDTO of(Cliente cliente) {
		return new ClienteDTO(cliente.getId(), cliente.getNome(), cliente.getEmail());
	}


}
