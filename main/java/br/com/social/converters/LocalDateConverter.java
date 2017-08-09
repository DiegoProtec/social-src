package br.com.social.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = LocalDate.class)
public class LocalDateConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String dataTexto) {
		LocalDate converter = null;

		dataTexto = dataTexto.trim();

		if (null == dataTexto || dataTexto.isEmpty()) {
			return null;
		}

		try {

			converter = LocalDate.parse(dataTexto, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

		} catch (DateTimeParseException e) {
			throw new ConverterException("O ano deve conter 4 dígitos. Exemplo: 13/11/2015.");
		}

		return converter;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object dataObjeto) {

		if (null == dataObjeto) {
			return null;
		}

		return ((LocalDate) dataObjeto).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

}
