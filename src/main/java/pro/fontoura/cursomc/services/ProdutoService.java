package pro.fontoura.cursomc.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pro.fontoura.cursomc.domain.Categoria;
import pro.fontoura.cursomc.domain.Produto;
import pro.fontoura.cursomc.dto.ProdutoDTO;
import pro.fontoura.cursomc.repositories.CategoriaRepository;
import pro.fontoura.cursomc.repositories.ProdutoRepository;
import pro.fontoura.cursomc.services.exceptions.DataIntegrityException;
import pro.fontoura.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService implements ServiceInterface<Produto, Produto> {

	@Autowired
	private ProdutoRepository produtoRepo;

	@Autowired
	private CategoriaRepository categoriaRepo;

	@Override
	public Produto find(Integer id) {
		Optional<Produto> obj = produtoRepo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(id, Produto.class.getName()));

	}

	public Page<Produto> search(String nome, List<Integer> ids, int page, int linesPerPages, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPages, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepo.findAllById(ids);
		return produtoRepo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}

	/**
	 * @param obj Um novo Produto a ser inserido na base de dados
	 * @return A instância monitorada do produto inserido
	 * 
	 * @see pro.fontoura.cursomc.services.ServiceInterface#insert(java.lang.Object)
	 **/
	@Transactional
	public Produto insert(Produto obj) {
		obj.setId(null);
		return produtoRepo.save(obj);
	}

	@Override
	public Produto update(Produto obj) {
		find(obj.getId());
		return produtoRepo.save(obj);
	}

	@Override
	public void delete(Integer id) {
		find(id);
		try {
			produtoRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir uma Produto que possui produtos relacionados.");
		}
	}

	@Override
	public List<Produto> findAll() {
		return produtoRepo.findAll();
	}

	@Override
	public Page<Produto> findToPages(int page, int linesPerPages, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPages, Direction.valueOf(direction), orderBy);
		return produtoRepo.findAll(pageRequest);
	}

	@Override
	public Produto fromDTO(Produto objDto) {
		return null;
	}

	public Produto fromDTO(@Valid ProdutoDTO objDto) {
		Produto produto = new Produto(null, objDto.getNome(), objDto.getPreco());
		return produto;
	}
}
