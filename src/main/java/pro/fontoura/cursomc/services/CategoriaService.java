package pro.fontoura.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pro.fontoura.cursomc.domain.Categoria;
import pro.fontoura.cursomc.repositories.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	
	public Categoria busca(Integer id) {
		Optional<Categoria> obj = repository.findById(id);
		
		return obj.orElse(null);
	}
}
