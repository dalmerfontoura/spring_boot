package pro.fontoura.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pro.fontoura.cursomc.domain.Cidade;
import pro.fontoura.cursomc.domain.Estado;
import pro.fontoura.cursomc.dto.CidadeDTO;
import pro.fontoura.cursomc.dto.EstadoDTO;
import pro.fontoura.cursomc.services.CidadeService;
import pro.fontoura.cursomc.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource implements ResourceInterface<Estado, EstadoDTO> {
	
	@Autowired
	EstadoService service;
	
	@Autowired
	CidadeService cidadeService;

	@Override
	public ResponseEntity<Estado> find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll() {
		List<Estado> estados = service.findAll();
		List<EstadoDTO> estadoDTOs =  estados.stream().map(obj -> new EstadoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok(estadoDTOs);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/{estado_id}/cidades")
	public List<CidadeDTO>findCidades(@PathVariable Integer estado_id) {
		List<Cidade> cidades = cidadeService.findCidades(new Estado(estado_id, null));
		List<CidadeDTO> cidadeDTOs = cidades.stream().map(obj -> new CidadeDTO(obj)).collect(Collectors.toList());
		return cidadeDTOs;
	}

	@Override
	public ResponseEntity<Page<EstadoDTO>> findPage(int page, int linesPerPages, String orderBy, String direction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> insert(EstadoDTO objDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Void> update(EstadoDTO objDto, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Estado> delete(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
