package br.com.social.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = LocalDateTime.class)
public class LocalDateTimeConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String dataTexto) {
		LocalDateTime converter = null;

		dataTexto = dataTexto.trim();

		if (null == dataTexto || dataTexto.isEmpty()) {
			return null;
		}

		try {

			converter = LocalDateTime.parse(dataTexto, DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss").withZone(ZoneId.systemDefault()));

		} catch (DateTimeParseException e) {
			throw new ConverterException("O formato da data e hora deve ser 13/11/2015 12:00:00.");
		}

		return converter;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object dataObjeto) {
		if (dataObjeto == null) {
			return "";
		}

		return ((LocalDateTime) dataObjeto).format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss").withZone(ZoneId.systemDefault()));
	}

}
