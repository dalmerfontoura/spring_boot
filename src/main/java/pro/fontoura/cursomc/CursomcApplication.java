package pro.fontoura.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import pro.fontoura.cursomc.domain.Cidade;
import pro.fontoura.cursomc.domain.Cliente;
import pro.fontoura.cursomc.domain.Endereco;
import pro.fontoura.cursomc.domain.ItemPedido;
import pro.fontoura.cursomc.domain.Pagamento;
import pro.fontoura.cursomc.domain.PagamentoComBoleto;
import pro.fontoura.cursomc.domain.PagamentoComCartao;
import pro.fontoura.cursomc.domain.Pedido;
import pro.fontoura.cursomc.domain.Produto;
import pro.fontoura.cursomc.domain.enuns.EstadoPagamento;
import pro.fontoura.cursomc.domain.enuns.TipoPessoa;
import pro.fontoura.cursomc.repositories.CidadeRepository;
import pro.fontoura.cursomc.repositories.ClienteRepository;
import pro.fontoura.cursomc.repositories.EnderecoRepository;
import pro.fontoura.cursomc.repositories.ItemPedidoRepository;
import pro.fontoura.cursomc.repositories.PagamentoRepository;
import pro.fontoura.cursomc.repositories.PedidoRepository;
import pro.fontoura.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepositor;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

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

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:30"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, e2);

		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);

		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, null,
				sdf.parse("20/10/2017 23:59"));
		ped2.setPagamento(pag2);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepositor.saveAll(Arrays.asList(ped1, ped2));

		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));

		Optional<Produto> p1 = produtoRepository.findById(1);
		Optional<Produto> p2 = produtoRepository.findById(2);
		Optional<Produto> p3 = produtoRepository.findById(3);

		ItemPedido ip1 = new ItemPedido(p1.get(), ped1, 1, 2000.00, (double) 0);
		ItemPedido ip2 = new ItemPedido(p3.get(), ped1, 2, 80.00, (double) 0);

		ItemPedido ip3 = new ItemPedido(p2.get(), ped2, 1, 800.0, 100.0);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().add(ip3);

		p1.get().setItens(new HashSet<>(Arrays.asList(ip1)));
		p2.get().setItens(new HashSet<>(Arrays.asList(ip3)));
		p3.get().setItens(new HashSet<>(Arrays.asList(ip2)));

		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}
}
