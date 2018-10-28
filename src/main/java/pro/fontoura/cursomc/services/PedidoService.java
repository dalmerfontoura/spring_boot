package pro.fontoura.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.fontoura.cursomc.domain.Pedido;
import pro.fontoura.cursomc.repositories.PedidoRepository;
import pro.fontoura.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public Pedido busca(Integer id) {
		Optional<Pedido> obj = repository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException( id, Pedido.class.getName()));

	}
}
