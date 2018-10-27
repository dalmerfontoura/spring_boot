package pro.fontoura.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.fontoura.cursomc.domain.Cliente;
import pro.fontoura.cursomc.repositories.ClienteRepository;
import pro.fontoura.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;

	public Cliente busca(Integer id) {
		Optional<Cliente> obj = repository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException( id, Cliente.class.getName()));

	}
}
