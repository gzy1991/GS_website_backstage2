package net.gslab.util;

import net.gslab.entity.User.ClassType;
import net.gslab.entity.User.Gender;

import org.springframework.core.convert.converter.Converter;

public class StringToGenderConverter implements Converter<String, Gender> {

	

	@Override
	public Gender convert(String source) {
		System.out.println("in the convert of the StringToGenderConverter");
		 String value = source.trim();
		    if ("".equals(value)) {
		      return null;
		    }
		    return Gender.get(source);

	}

}
