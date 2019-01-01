package br.com.lfuso.cursos.spring.restful.order.domain;

import br.com.lfuso.cursos.spring.restful.order.domain.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PagamentoComBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyy HH:mm")
	private Date vencimento;

	@JsonFormat(pattern = "dd/MM/yyy HH:mm")
	private Date pagamento;

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date vencimento, Date pagamento) {
		super(id, estado, pedido);
		this.vencimento = vencimento;
		this.pagamento = pagamento;
	}
}
