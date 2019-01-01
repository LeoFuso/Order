package br.com.lfuso.cursos.spring.restful.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Cidade implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "estado_id")
	private Estado estado;

	public Cidade(Integer id, String nome, Estado estado) {
		this.id = id;
		this.nome = nome;
		this.estado = estado;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cidade cidade = (Cidade) o;

		return Objects.equals(getNome(), cidade.getNome()) &&
				Objects.equals(getEstado(), cidade.getEstado());

	}

	@Override
	public int hashCode() {
		return Objects.hash(getNome(), getEstado());
	}
}
