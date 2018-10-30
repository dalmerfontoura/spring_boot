package pro.fontoura.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import pro.fontoura.cursomc.services.DBService;

@Configuration
@Profile("test")
public class TesteConfig {
	
	@Autowired
	public DBService service;

	@Bean
	public boolean instantiateDatabase() throws ParseException {
		service.instantiateDatabase();
		return true;
	}
}
