package pro.fontoura.cursomc.domain.enuns;

public enum EstadoPagamento {

	PENDENTE(1, "Pendente"), QUITADO(2, "Quitado"), CANCELADO(3, "Cancelado");

	private int id;
	private String nome;

	private EstadoPagamento(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public static EstadoPagamento toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (EstadoPagamento iterable_element : EstadoPagamento.values()) {
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
