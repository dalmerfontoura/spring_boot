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

import pro.fontoura.cursomc.domain.Cliente;
import pro.fontoura.cursomc.dto.ClienteDTO;
import pro.fontoura.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource extends ResourceDefault implements ResourceInterface<Cliente, ClienteDTO> {

	@Autowired
	private ClienteService service;
	
	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
		Cliente obj = service.find(id);
	
		return ResponseEntity.ok().body(obj);
		
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> clientes = service.findAll();
		List<ClienteDTO> clienteDTOs = clientes.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(clienteDTOs);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(name= "page", defaultValue = "0") int page, 
			@RequestParam(name= "linesPerPages", defaultValue = "24") int linesPerPages, 
			@RequestParam(name= "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(name= "direction", defaultValue = "ASC") String direction) {

		Page<Cliente> clientes = service.findToPages(page, linesPerPages, orderBy, direction);

		Page<ClienteDTO> clienteDTOs = clientes.map(obj -> new ClienteDTO(obj));

		return ResponseEntity.ok().body(clienteDTOs);
	}

	@Override
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(ClienteDTO objDto) {
		Cliente obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = retornarURI(obj.getId());
		return ResponseEntity.created(uri).build();
	}

	@Override
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {
		Cliente obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@Override
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Cliente> delete(@PathVariable Integer id) {
		service.delete(id);

		return ResponseEntity.ok().build();
	}
}
