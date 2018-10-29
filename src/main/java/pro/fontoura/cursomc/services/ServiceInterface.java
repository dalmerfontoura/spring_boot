package pro.fontoura.cursomc.services;

import java.util.List;

import org.springframework.data.domain.Page;

public interface ServiceInterface<T, T1> {

	/**
	 * @param id
	 * @return
	 */
	T find(Integer id);

	/**
	 * @param obj
	 * @return
	 */
	T insert(T obj);

	/**
	 * @param obj
	 * @return
	 */
	T update(T obj);

	/**
	 * @param id
	 */
	void delete(Integer id);

	/**
	 * @return
	 */
	List<T> findAll();

	/**
	 * @param page
	 * @param linesPerPages
	 * @param orderBy
	 * @param direction
	 * @return
	 */
	Page<T> findToPages(int page, int linesPerPages, String orderBy, String direction);

	/**
	 * @param objDto
	 * @return
	 */
	T fromDTO(T1 objDto);

}