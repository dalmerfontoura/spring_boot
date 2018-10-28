package pro.fontoura.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pro.fontoura.cursomc.domain.Cliente;
import pro.fontoura.cursomc.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource extends ResourceDefault {

	@Autowired
	private ClienteService service;
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Cliente obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
		
	}
}
