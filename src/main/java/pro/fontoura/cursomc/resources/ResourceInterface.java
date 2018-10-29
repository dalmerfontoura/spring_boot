package pro.fontoura.cursomc.resources;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface ResourceInterface<T, V> {

	/**
	 * @param id
	 * @return
	 */
	ResponseEntity<T> find(Integer id);

	/**
	 * @return
	 */
	ResponseEntity<List<V>> findAll();

	/**
	 * @return
	 */
	ResponseEntity<Page<V>> findPage(int page, int linesPerPages, String orderBy, String direction);

	ResponseEntity<Void> insert(V objDto);

	ResponseEntity<Void> update(V objDto, Integer id);

	ResponseEntity<T> delete(Integer id);

}