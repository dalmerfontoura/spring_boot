package pro.fontoura.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import pro.fontoura.cursomc.domain.Estado;
import pro.fontoura.cursomc.repositories.EstadoRepository;

@Service
public class EstadoService implements ServiceInterface<Estado> {
	
	@Autowired
	EstadoRepository repo;

	@Override
	public Estado find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Estado> findAll() {
		return repo.findAllByOrderByNome();
	}

	@Override
	public Page<Estado> findToPages(int page, int linesPerPages, String orderBy, String direction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Estado insert(Estado obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Estado update(Estado obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
