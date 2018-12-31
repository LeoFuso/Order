package br.com.lfuso.cursos.spring.restful.order.domain.enums;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(3, "Cancelado");

	private Integer codigo;
	private String descricao;

	EstadoPagamento(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public static EstadoPagamento toEnum(Integer codigo) {
		if (codigo == null)
			return null;

		for (EstadoPagamento ep : EstadoPagamento.values()) {
			if (codigo.equals(ep.getCodigo()))
				return ep;
		}

		throw new IllegalArgumentException("Código inválido: " + codigo);
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
}
