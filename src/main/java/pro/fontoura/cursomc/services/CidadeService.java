package pro.fontoura.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import pro.fontoura.cursomc.domain.Cidade;
import pro.fontoura.cursomc.domain.Estado;
import pro.fontoura.cursomc.repositories.CidadeRepository;
import pro.fontoura.cursomc.services.exceptions.DataIntegrityException;
import pro.fontoura.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CidadeService implements ServiceInterface<Cidade> {
	
	@Autowired
	CidadeRepository repo;

	@Override
	public Cidade find(Integer id) {
		Optional<Cidade> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(id, Cidade.class.getName()));
	}

	@Override
	public List<Cidade> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Cidade> findToPages(int page, int linesPerPages, String orderBy, String direction) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Cidade> findCidades(Estado estado) {
		return repo.findCidades(estado.getId());
	}

	@Override
	public Cidade insert(Cidade obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	@Override
	public Cidade update(Cidade obj) {
		find(obj.getId());
		return repo.save(obj);
	}

	@Override
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma cidade que possui endcereço relacionados.");
		}
	}

}
