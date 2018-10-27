package pro.fontoura.cursomc.domain.enuns;

public enum TipoPessoa {

	PESSOAFISICA(1, "Pessoa Fisica"), PESSOAJURIDICA(2, "Pessoa Juridica");

	private int id;
	private String nome;

	private TipoPessoa(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public static TipoPessoa toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (TipoPessoa iterable_element : TipoPessoa.values()) {
			if (cod.equals(iterable_element.getId())) {
				return iterable_element;
			}
		}

		throw new IllegalArgumentException("Id invalido: " + cod);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

}
