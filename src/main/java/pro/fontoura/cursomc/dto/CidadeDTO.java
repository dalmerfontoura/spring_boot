package pro.fontoura.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import pro.fontoura.cursomc.domain.Cidade;

public class CidadeDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotEmpty(message = "Preechimento Obrigatório")
	private String nome;

	public CidadeDTO() {

	}

	public CidadeDTO(Cidade cidade) {
		this.id = cidade.getId();
		this.nome = cidade.getNome();
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
