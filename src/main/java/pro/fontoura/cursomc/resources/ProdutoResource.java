package pro.fontoura.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pro.fontoura.cursomc.domain.Produto;
import pro.fontoura.cursomc.dto.ProdutoDTO;
import pro.fontoura.cursomc.resources.util.URL;
import pro.fontoura.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource extends ResourceDefault implements ResourceInterface<Produto, ProdutoDTO> {

	@Autowired
	private ProdutoService service;

	/*
	 * (non-Javadoc)
	 * 
	 * @see pro.fontoura.cursomc.resources.ResourceInterface#find(java.lang.Integer)
	 */
	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Produto> find(@PathVariable Integer id) {

		Produto obj = service.find(id);

		return ResponseEntity.ok().body(obj);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pro.fontoura.cursomc.resources.ResourceInterface#findAll()
	 */
	@Override
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ProdutoDTO>> findAll() {

		List<Produto> produtos = service.findAll();

		List<ProdutoDTO> categoriaDTOs = produtos.stream().map(obj -> new ProdutoDTO(obj))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(categoriaDTOs);

	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/page")
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "linesPerPages", defaultValue = "24") int linesPerPages,
			@RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction) {
		Page<Produto> categorias = service.findToPages(page, linesPerPages, orderBy, direction);

		Page<ProdutoDTO> categoriaDTOs = categorias.map(obj -> new ProdutoDTO(obj));

		return ResponseEntity.ok().body(categoriaDTOs);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see pro.fontoura.cursomc.resources.ResourceInterface#findPage(int, int,
	 * java.lang.String, java.lang.String)
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/pageNomeCategoria")
	public ResponseEntity<Page<ProdutoDTO>> findPageNomeCategoria(
			@RequestParam(name = "nome", defaultValue = "0") String nome,
			@RequestParam(name = "categorias", defaultValue = "0") String categorias,
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "linesPerPages", defaultValue = "24") int linesPerPages,
			@RequestParam(name = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(name = "direction", defaultValue = "ASC") String direction) {
		
		List<Integer> ids = URL.decodeIntList(categorias);

		Page<Produto> produtos = service.search(URL.decoded(nome), ids, page, linesPerPages, orderBy, direction);

		Page<ProdutoDTO> produtoDTOs = produtos.map(obj -> new ProdutoDTO(obj));

		return ResponseEntity.ok().body(produtoDTOs);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pro.fontoura.cursomc.resources.ResourceInterface#insert(pro.fontoura.cursomc.
	 * dto.ProdutoDTO)
	 */
	@Override
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ProdutoDTO objDto) {
		Produto obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = retornarURI(obj.getId());
		return ResponseEntity.created(uri).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pro.fontoura.cursomc.resources.ResourceInterface#update(pro.fontoura.cursomc.
	 * dto.ProdutoDTO, java.lang.Integer)
	 */
	@Override
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ProdutoDTO objDto, @PathVariable Integer id) {
		Produto obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * pro.fontoura.cursomc.resources.ResourceInterface#delete(java.lang.Integer)
	 */
	@Override
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Produto> delete(@PathVariable Integer id) {

		service.delete(id);

		return ResponseEntity.ok().build();

	}
}
