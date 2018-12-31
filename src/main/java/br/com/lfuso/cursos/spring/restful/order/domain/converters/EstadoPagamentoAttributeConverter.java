package br.com.lfuso.cursos.spring.restful.order.domain.converters;

import br.com.lfuso.cursos.spring.restful.order.domain.enums.EstadoPagamento;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class EstadoPagamentoAttributeConverter implements AttributeConverter<EstadoPagamento, Integer> {

	@Override
	public Integer convertToDatabaseColumn(EstadoPagamento attribute) {
		return attribute.getCodigo();
	}

	@Override
	public EstadoPagamento convertToEntityAttribute(Integer dbData) {
		return EstadoPagamento.toEnum(dbData);
	}

}
