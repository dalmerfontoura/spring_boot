package pro.fontoura.cursomc.resources;

import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import pro.fontoura.cursomc.domain.Categoria;

public class ResourceDefault {

	public ResourceDefault() {
		super();
	}

	/**
	 * Retorna a URL do objeto rm uso
	 * @param obj
	 * @return
	 */
	protected URI retornarURI(Categoria obj) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
	}

}