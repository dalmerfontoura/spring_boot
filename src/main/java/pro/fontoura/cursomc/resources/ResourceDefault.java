package pro.fontoura.cursomc.resources;

import java.net.URI;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ResourceDefault {

	public ResourceDefault() {
		super();
	}

	/**
	 * Retorna a URL do objeto rm uso
	 * @param id
	 * @return
	 */
	protected URI retornarURI(Integer id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

}