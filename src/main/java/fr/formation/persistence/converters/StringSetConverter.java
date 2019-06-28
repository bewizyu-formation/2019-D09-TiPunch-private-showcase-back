package fr.formation.persistence.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * The type String set converter.
 */
@Converter
public class StringSetConverter implements AttributeConverter<Set<String>, String> {

	private static final String SPLIT_CHAR = ";";

	@Override
	public String convertToDatabaseColumn(Set<String> strings) {
		return String.join(SPLIT_CHAR, strings);
	}

	@Override
	public Set<String> convertToEntityAttribute(String s) {
		return new HashSet<>(Arrays.asList(s.split(SPLIT_CHAR)));
	}
}
