package br.com.lfuso.cursos.spring.restful.order.domain.converters;

import br.com.lfuso.cursos.spring.restful.order.domain.enums.TipoCliente;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class TipoClienteAttributeConverter implements AttributeConverter<TipoCliente, Integer> {

	@Override
	public Integer convertToDatabaseColumn(TipoCliente attribute) {
		return attribute.getCodigo();
	}

	@Override
	public TipoCliente convertToEntityAttribute(Integer dbData) {
		return TipoCliente.toEnum(dbData);
	}

}
