package pro.fontoura.cursomc.domain.enuns;

public enum Perfil {

	ADMIN(1, "ROLE_ADMIN"), CLIENTE(2, "ROLE_CLIENTE");

	private int id;
	private String nome;

	private Perfil(int id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public static Perfil toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}
		for (Perfil iterable_element : Perfil.values()) {
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
