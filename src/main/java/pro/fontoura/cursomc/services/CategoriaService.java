package pro.fontoura.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import pro.fontoura.cursomc.domain.Categoria;
import pro.fontoura.cursomc.dto.CategoriaDTO;
import pro.fontoura.cursomc.repositories.CategoriaRepository;
import pro.fontoura.cursomc.services.exceptions.DataIntegrityException;
import pro.fontoura.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService implements ServiceInterface<Categoria, CategoriaDTO> {

	@Autowired
	private CategoriaRepository repository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see pro.fontoura.cursomc.services.ServiceInterface#find(java.lang.Integer)
	 */
	@Override
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repository.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(id, Categoria.class.getName()));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pro.fontoura.cursomc.services.ServiceInterface#insert(pro.fontoura.cursomc.domain.Categoria)
	 */
	@Override
	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pro.fontoura.cursomc.services.ServiceInterface#update(pro.fontoura.cursomc.domain.Categoria)
	 */
	@Override
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repository.save(obj);
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
			throw new DataIntegrityException("Não é possivel excluir uma categoria que possui produtos relacionados.");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pro.fontoura.cursomc.services.ServiceInterface#findAll()
	 */
	@Override
	public List<Categoria> findAll() {
		return repository.findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pro.fontoura.cursomc.services.ServiceInterface#findToPages(int, int, java.lang.String, java.lang.String)
	 */
	@Override
	public Page<Categoria> findToPages(int page, int linesPerPages, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPages, Direction.valueOf(direction), orderBy);

		return repository.findAll(pageRequest);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pro.fontoura.cursomc.services.ServiceInterface#fromDTO(pro.fontoura.cursomc.dto.CategoriaDTO)
	 */
	@Override
	public Categoria fromDTO(CategoriaDTO objDto) {
		Categoria obj = new Categoria(objDto.getId(), objDto.getNome());
		return obj;
	}
}
