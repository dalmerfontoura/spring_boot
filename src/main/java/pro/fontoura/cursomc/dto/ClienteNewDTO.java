package pro.fontoura.cursomc.dto;

import java.io.Serializable;

public class ClienteNewDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String email;
	private String cpfOuCnpj;
	private Integer tipo;

	private String logradouro;
	private Integer numero;
	private String complemento;
	private String bairro;
	private String cep;

	private String telefone1;
	private String telefone2;
	private String telefone3;

	private Integer cidadeId;

	public ClienteNewDTO() {

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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the cpfOuCnpj
	 */
	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	/**
	 * @param cpfOuCnpj the cpfOuCnpj to set
	 */
	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	/**
	 * @return the tipo
	 */
	public Integer getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the logradouro
	 */
	public String getLogradouro() {
		return logradouro;
	}

	/**
	 * @param logradouro the logradouro to set
	 */
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	/**
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/**
	 * @return the complemento
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * @param complemento the complemento to set
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * @return the bairro
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * @param bairro the bairro to set
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * @return the cep
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * @param cep the cep to set
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/**
	 * @return the telefone1
	 */
	public String getTelefone1() {
		return telefone1;
	}

	/**
	 * @param telefone1 the telefone1 to set
	 */
	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	/**
	 * @return the telefone2
	 */
	public String getTelefone2() {
		return telefone2;
	}

	/**
	 * @param telefone2 the telefone2 to set
	 */
	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	/**
	 * @return the telefone3
	 */
	public String getTelefone3() {
		return telefone3;
	}

	/**
	 * @param telefone3 the telefone3 to set
	 */
	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	/**
	 * @return the cidadeId
	 */
	public Integer getCidadeId() {
		return cidadeId;
	}

	/**
	 * @param cidadeId the cidadeId to set
	 */
	public void setCidadeId(Integer cidadeId) {
		this.cidadeId = cidadeId;
	}

}
