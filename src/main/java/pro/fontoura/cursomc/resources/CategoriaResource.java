package pro.fontoura.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pro.fontoura.cursomc.domain.Categoria;
import pro.fontoura.cursomc.dto.CategoriaDTO;
import pro.fontoura.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource extends ResourceDefault implements ResourceInterface<Categoria, CategoriaDTO> {

	@Autowired
	private CategoriaService service;

	/* (non-Javadoc)
	 * @see pro.fontoura.cursomc.resources.CRUDInterface#find(java.lang.Integer)
	 */
	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {

		Categoria obj = service.find(id);

		return ResponseEntity.ok().body(obj);

	}

	/* (non-Javadoc)
	 * @see pro.fontoura.cursomc.resources.CRUDInterface#findAll()
	 */
	@Override
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {

		List<Categoria> categorias = service.findAll();

		List<CategoriaDTO> categoriaDTOs = categorias.stream().map(obj -> new CategoriaDTO(obj))
				.collect(Collectors.toList());

		return ResponseEntity.ok().body(categoriaDTOs);

	}

	/* (non-Javadoc)
	 * @see pro.fontoura.cursomc.resources.CRUDInterface#findPage(int, int, java.lang.String, java.lang.String)
	 */
	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			@RequestParam(name= "page", defaultValue = "0") int page, 
			@RequestParam(name= "linesPerPages", defaultValue = "24") int linesPerPages, 
			@RequestParam(name= "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(name= "direction", defaultValue = "ASC") String direction) {

		Page<Categoria> categorias = service.findToPages(page, linesPerPages, orderBy, direction);

		Page<CategoriaDTO> categoriaDTOs = categorias.map(obj -> new CategoriaDTO(obj));

		return ResponseEntity.ok().body(categoriaDTOs);

	}

	/* (non-Javadoc)
	 * @see pro.fontoura.cursomc.resources.CRUDInterface#insert(pro.fontoura.cursomc.dto.CategoriaDTO)
	 */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@Override
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto) {
		Categoria obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = retornarURI(obj.getId());
		return ResponseEntity.created(uri).build();
	}

	/* (non-Javadoc)
	 * @see pro.fontoura.cursomc.resources.CRUDInterface#update(pro.fontoura.cursomc.dto.CategoriaDTO, java.lang.Integer)
	 */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@Override
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id) {
		Categoria obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	/* (non-Javadoc)
	 * @see pro.fontoura.cursomc.resources.CRUDInterface#delete(java.lang.Integer)
	 */
	@PreAuthorize("hasAnyRole('ADMIN')")
	@Override
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Categoria> delete(@PathVariable Integer id) {

		service.delete(id);

		return ResponseEntity.ok().build();

	}
}
