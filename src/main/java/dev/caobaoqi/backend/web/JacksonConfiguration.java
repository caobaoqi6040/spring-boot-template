package dev.caobaoqi.backend.web;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonConfiguration {

	public static final String DATE_FORMATTER_PATTERN = "YYYY-MM-dd HH:mm:ss";
	public static final String LOCAL_TIME_FORMATTER_PATTERN = "HH:mm:ss";
	public static final String LOCAL_DATE_FORMATTER_PATTERN = "YYYY-MM-dd";
	public static final String LOCAL_DATE_TIME_FORMATTER_PATTERN = "YYYY-MM-dd HH:mm:ss";

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
		// TODO("what`s jackson !!!!!!!!!")
		return builder -> {
			builder.simpleDateFormat(DATE_FORMATTER_PATTERN);
			builder.serializers(
				new LocalTimeSerializer(DateTimeFormatter.ofPattern(LOCAL_TIME_FORMATTER_PATTERN)),
				new LocalDateSerializer(DateTimeFormatter.ofPattern(LOCAL_DATE_FORMATTER_PATTERN)),
				new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_FORMATTER_PATTERN))
			);
		};
	}

}
