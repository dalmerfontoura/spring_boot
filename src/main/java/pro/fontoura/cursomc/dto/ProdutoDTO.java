package pro.fontoura.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import pro.fontoura.cursomc.domain.Produto;

public class ProdutoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	@NotEmpty
	private String nome;

	private Double preco;

	public ProdutoDTO(Produto obj) {
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.preco = obj.getPreco();
	}
	
	public Integer getId() {
		return id;
	}
	
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

	/**
	 * @return the preco
	 */
	public Double getPreco() {
		return preco;
	}

	/**
	 * @param preco the preco to set
	 */
	public void setPreco(Double preco) {
		this.preco = preco;
	}

}
