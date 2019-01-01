package br.com.lfuso.cursos.spring.restful.order.resources.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class LocaleDateTimeStdSerializer extends StdSerializer<LocalDateTime> {

	public LocaleDateTimeStdSerializer() {
		this(null);
	}

	private LocaleDateTimeStdSerializer(Class<LocalDateTime> t) {
		super(t);
	}

	@Override
	public void serialize(LocalDateTime value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		long epochMilli = value
				.atZone(ZoneId.systemDefault())
				.toInstant().getEpochSecond();

		jsonGenerator.writeString(String.valueOf(epochMilli));
	}
}
