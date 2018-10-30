package pro.fontoura.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import pro.fontoura.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Value("${default.recipient}")
	private String recipient;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido pedido) {
		SimpleMailMessage smm = prepareSimpleMailMessageFromPedido(pedido);
		sendEmail(smm);
	}

	private SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setTo(pedido.getCliente().getEmail());
		smm.setFrom(sender);
		smm.setSubject("Pedido Confirmado! Códig: " + pedido.getId());
		smm.setSentDate(new Date(System.currentTimeMillis()));
		smm.setText(pedido.toString());
		
		return smm;
	}

	

}
