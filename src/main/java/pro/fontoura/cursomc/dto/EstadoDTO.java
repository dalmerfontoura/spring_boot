package pro.fontoura.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import pro.fontoura.cursomc.domain.Estado;

public class EstadoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "Preechimento Obrigatório")
	private String nome;
	
	public EstadoDTO() {
		
	}

	public EstadoDTO(Estado estado) {
		this.id = estado.getId();
		this.nome = estado.getNome();
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
