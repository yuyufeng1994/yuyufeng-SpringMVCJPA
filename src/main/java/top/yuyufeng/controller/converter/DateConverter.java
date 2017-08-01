package top.yuyufeng.controller.converter;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter implements Converter<String, Date> {
	public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public Date convert(String source) {
		// 将日期串转成日起类型(格式是 yyyy-MM-dd)
		try {
			return simpleDateFormat.parse(source);
		} catch (ParseException e) {
			return null;
		}
	}
	
	

}
