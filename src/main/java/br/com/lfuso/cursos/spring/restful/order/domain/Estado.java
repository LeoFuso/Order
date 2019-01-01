package br.com.lfuso.cursos.spring.restful.order.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Estado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;

	@OneToMany(mappedBy = "estado")
	private Set<Cidade> cidades = new HashSet<>();

	public Estado(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Estado estado = (Estado) o;
		return Objects.equals(getId(), estado.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
