package br.com.social.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LocalDateTimeAttributeConverter implements AttributeConverter<LocalDateTime, Timestamp> {

	@Override
	public Timestamp convertToDatabaseColumn(LocalDateTime dateTime) {
		return (dateTime == null ? null : Timestamp.valueOf(dateTime));
	}

	@Override
	public LocalDateTime convertToEntityAttribute(Timestamp time) {
		return (time == null ? null : time.toLocalDateTime());
	}

}
