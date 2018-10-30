package pro.fontoura.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import pro.fontoura.cursomc.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);

}
