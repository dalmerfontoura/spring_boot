package pro.fontoura.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import pro.fontoura.cursomc.services.DBService;
import pro.fontoura.cursomc.services.EmailService;
import pro.fontoura.cursomc.services.MockMailService;

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
	
	@Bean
	public EmailService emailService() {
		return new MockMailService();
	}
}
