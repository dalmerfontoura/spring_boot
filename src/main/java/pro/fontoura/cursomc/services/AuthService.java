package pro.fontoura.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pro.fontoura.cursomc.domain.Cliente;
import pro.fontoura.cursomc.repositories.ClienteRepository;
import pro.fontoura.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private BCryptPasswordEncoder bCrypt;

	@Autowired
	private EmailService emailService;

	Random random = new Random();

	public void sendNewPassword(String email) {
		Cliente cliente = clienteRepository.findByEmail(email);
		if (cliente == null) {
			throw new ObjectNotFoundException("E-mail não encontrado");
		}

		String newPass = newPassword();
		cliente.setSenha(bCrypt.encode(newPass));

		clienteRepository.save(cliente);

		emailService.sendNewPasswordEmail(cliente, newPass);
	}

	private String newPassword() {
		char[] vet = new char[10];
		for (int i = 0; i < 10; i++) {
			vet[i] = randomChar();
		}
		return new String(vet);
	}

	private char randomChar() {
		int opt = random.nextInt(3);
		switch (opt) { 
		case 1: //gera um digito
			return (char) (random.nextInt(10) + 48 );
		case 2: // gera letra maiuscula
			return (char) (random.nextInt(26) + 65 );
		default: //gera letra minuscula
			return (char) (random.nextInt(26) + 97 );
		}
	}

}
