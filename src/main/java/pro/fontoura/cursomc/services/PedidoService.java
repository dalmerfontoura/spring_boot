package pro.fontoura.cursomc.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pro.fontoura.cursomc.domain.ItemPedido;
import pro.fontoura.cursomc.domain.PagamentoComBoleto;
import pro.fontoura.cursomc.domain.Pedido;
import pro.fontoura.cursomc.domain.enuns.EstadoPagamento;
import pro.fontoura.cursomc.repositories.ItemPedidoRepository;
import pro.fontoura.cursomc.repositories.PagamentoRepository;
import pro.fontoura.cursomc.repositories.PedidoRepository;
import pro.fontoura.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public Pedido find(Integer id) {
		Optional<Pedido> obj = repository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException( id, Pedido.class.getName()));

	}

	@Transactional
	public Pedido insert(Pedido obj) {
		obj.setInstante(new Date());
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
			ip.setPreco(produtoService.find(ip.getProduto().getId()).getPreco());
			
		}
		itemPedidoRepository.saveAll(obj.getItens());
		return obj;
	}
}
