package pro.fontoura.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.springframework.mail.SimpleMailMessage;

public class MockMailService extends AbstractEmailService {

	private static Logger LOG = org.slf4j.LoggerFactory.getLogger(MockMailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Simulando envio de e-mail...");
		LOG.info(msg.toString());
		LOG.info("e-mail enviado!");
	}

	@Override
	public void sendHtmlEmail(MimeMessage msg) {
		LOG.info("Simulando envio de e-mail Html...");
		LOG.info(msg.toString());
		LOG.info("e-mail enviado!");
		
	}

}
