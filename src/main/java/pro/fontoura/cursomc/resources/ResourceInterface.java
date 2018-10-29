package pro.fontoura.cursomc.resources;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface ResourceInterface<T, T1> {

	/**
	 * @param id
	 * @return
	 */
	ResponseEntity<T> find(Integer id);

	/**
	 * @return
	 */
	ResponseEntity<List<T1>> findAll();

	/**
	 * @return
	 */
	ResponseEntity<Page<T1>> findPage(int page, int linesPerPages, String orderBy, String direction);

	ResponseEntity<Void> insert(T1 objDto);

	ResponseEntity<Void> update(T1 objDto, Integer id);

	ResponseEntity<T> delete(Integer id);

}