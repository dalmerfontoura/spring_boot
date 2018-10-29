package pro.fontoura.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import pro.fontoura.cursomc.domain.Cliente;
import pro.fontoura.cursomc.dto.ClienteDTO;
import pro.fontoura.cursomc.repositories.ClienteRepository;
import pro.fontoura.cursomc.services.exceptions.DataIntegrityException;
import pro.fontoura.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService implements ServiceInterface<Cliente, ClienteDTO> {
	@Autowired
	private ClienteRepository repository;

	public Cliente find(Integer id) {
		Optional<Cliente> obj = repository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException( id, Cliente.class.getName()));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pro.fontoura.cursomc.services.ServiceInterface#insert(pro.fontoura.cursomc.domain.Cliente)
	 */
	@Override
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pro.fontoura.cursomc.services.ServiceInterface#update(pro.fontoura.cursomc.domain.Cliente)
	 */
	@Override
	public Cliente update(Cliente obj) {
		Cliente newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(newObj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pro.fontoura.cursomc.services.ServiceInterface#delete(java.lang.Integer)
	 */
	@Override
	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir um Cliente que possui entidades relacionadas.");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pro.fontoura.cursomc.services.ServiceInterface#findAll()
	 */
	@Override
	public List<Cliente> findAll() {
		return repository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pro.fontoura.cursomc.services.ServiceInterface#findToPages(int, int, java.lang.String, java.lang.String)
	 */
	@Override
	public Page<Cliente> findToPages(int page, int linesPerPages, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPages, Direction.valueOf(direction), orderBy);

		return repository.findAll(pageRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pro.fontoura.cursomc.services.ServiceInterface#fromDTO(pro.fontoura.cursomc.dto.ClienteDTO)
	 */
	@Override
	public Cliente fromDTO(ClienteDTO objDto) {
		Cliente obj = new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
		return obj;
	}
	
	private void updateData(Cliente newObj, Cliente obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	}
}
