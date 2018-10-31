package pro.fontoura.cursomc.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pro.fontoura.cursomc.domain.Cliente;
import pro.fontoura.cursomc.domain.ItemPedido;
import pro.fontoura.cursomc.domain.PagamentoComBoleto;
import pro.fontoura.cursomc.domain.Pedido;
import pro.fontoura.cursomc.domain.enuns.EstadoPagamento;
import pro.fontoura.cursomc.repositories.ItemPedidoRepository;
import pro.fontoura.cursomc.repositories.PagamentoRepository;
import pro.fontoura.cursomc.repositories.PedidoRepository;
import pro.fontoura.cursomc.security.UserSpringSecurity;
import pro.fontoura.cursomc.services.exceptions.AuthorizationException;
import pro.fontoura.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService implements ServiceInterface<Pedido, Pedido> {

	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;
	
	public PedidoService() {
	}

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException( id, Pedido.class.getName()));

	}
	
	@Override
	public List<Pedido> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Page<Pedido> findToPages(int page, int linesPerPages, String orderBy, String direction) {
		
		UserSpringSecurity userSS = UserService.authenticated();
		if(userSS == null) {
			throw new AuthorizationException("Acesso Negado!");
		}
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPages, Direction.valueOf(direction), orderBy);
		Cliente cliente = clienteService.find(userSS.getId());
		
		return repository.findByCliente(cliente, pageRequest);
	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setInstante(new Date());
		obj.setCliente(clienteService.find(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if(obj.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagto = (PagamentoComBoleto) obj.getPagamento();
			boletoService.dataPagamentoBoleto(pagto, obj.getInstante());
		}
		
		obj = repository.save(obj);
		pagamentoRepository.save(obj.getPagamento());
		for (ItemPedido	ip: obj.getItens()) {
			ip.setDesconto(0.0);
			ip.setPedido(obj);
			ip.setProduto(produtoService.find(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			
		}
		itemPedidoRepository.saveAll(obj.getItens());
		emailService.sendOrderConfirmationHtmlEmail(obj);
		return obj;
	}
	
	@Override
	public Pedido update(Pedido obj) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Pedido fromDTO(Pedido objDto) {
		// TODO Auto-generated method stub
		return null;
	}
}
