package pro.fontoura.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pro.fontoura.cursomc.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		
		Categoria cat1 = new Categoria(1, "Informática");
		Categoria cat2 = new Categoria(2, "Escritório");
		
		List<Categoria> lst = new ArrayList<Categoria>();
		lst.add(cat1);
		lst.add(cat2);
		
		return lst;
		
	}
}
