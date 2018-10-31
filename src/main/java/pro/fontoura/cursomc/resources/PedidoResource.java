package pro.fontoura.cursomc.resources;

import java.net.URI;

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

import pro.fontoura.cursomc.domain.Pedido;
import pro.fontoura.cursomc.services.PedidoService;

@RestController
@RequestMapping(value="/pedidos")
public class PedidoResource extends ResourceDefault {

	@Autowired
	private PedidoService service;
	
	@RequestMapping(method = RequestMethod.GET, value="/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		Pedido obj = service.find(id);
		
		return ResponseEntity.ok().body(obj);
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Pedido>> findPage(
			@RequestParam(name = "page", defaultValue = "0") int page,
			@RequestParam(name = "linesPerPages", defaultValue = "24") int linesPerPages,
			@RequestParam(name = "orderBy", defaultValue = "instante") String orderBy,
			@RequestParam(name = "direction", defaultValue = "DESC") String direction) {

		Page<Pedido> pedidos = service.findToPages(page, linesPerPages, orderBy, direction);
		return ResponseEntity.ok().body(pedidos);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Pedido obj) {
		obj = service.insert(obj);
		URI uri = retornarURI(obj.getId());
		return ResponseEntity.created(uri).build();
	}
}
