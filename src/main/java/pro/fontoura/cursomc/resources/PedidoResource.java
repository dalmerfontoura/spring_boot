package pro.fontoura.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
}
