package br.com.lfuso.cursos.spring.restful.order.domain;

import br.com.lfuso.cursos.spring.restful.order.domain.enums.EstadoPagamento;
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
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	private EstadoPagamento estado;

	@OneToOne
	@JoinColumn(name = "pedido_id")
	@MapsId
	private Pedido pedido;

	public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
		this.id = id;
		this.estado = estado;
		this.pedido = pedido;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pagamento pagamento = (Pagamento) o;
		return Objects.equals(getId(), pagamento.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
