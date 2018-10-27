package pro.fontoura.cursomc;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pro.fontoura.cursomc.domain.Cidade;
import pro.fontoura.cursomc.domain.Cliente;
import pro.fontoura.cursomc.domain.Endereco;
import pro.fontoura.cursomc.domain.enuns.TipoPessoa;
import pro.fontoura.cursomc.repositories.CidadeRepository;
import pro.fontoura.cursomc.repositories.ClienteRepository;
import pro.fontoura.cursomc.repositories.EnderecoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner  {

	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoPessoa.PESSOAFISICA);

		cli1.getTelefone().addAll(Arrays.asList("27363323", "93838393"));
		
		Optional<Cidade> c1 = cidadeRepository.findById(1);
		Optional<Cidade> c2 = cidadeRepository.findById(2);
				

		Endereco e1 = new Endereco(null, "Rua Flores", 300, "Apto 303", "Jardim", "38220834", cli1, c1.get());
		Endereco e2 = new Endereco(null, "Avenida Matos", 105, "Sala 800", "Centro", "38777012", cli1, c2.get());
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	}
}
