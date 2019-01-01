package br.com.lfuso.cursos.spring.restful.order.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ItemPedido implements Serializable {

	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();

	private Double desconto;
	private Integer quantidade;
	private Double preco;

	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
		this.id.setPedido(pedido);
		this.id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}

	@JsonIgnore
	public Pedido getPedido() {
		return this.id.getPedido();
	}

	public Produto getProduto() {
		return this.id.getProduto();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ItemPedido that = (ItemPedido) o;
		return Objects.equals(getId(), that.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
