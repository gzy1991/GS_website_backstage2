package net.gslab.util;

import net.gslab.entity.User.ClassType;

import org.springframework.core.convert.converter.Converter;

public class StringToClassTypeConverter implements Converter<String, ClassType> {

	

	@Override
	public ClassType convert(String source) {
		 String value = source.trim();
		    if ("".equals(value)) {
		      return null;
		    }
		   //System.out.println(Integer.parseInt(source));
		    //Integer.parseInt(String)中的string为数字串
		    return ClassType.get(source);

	}

}
