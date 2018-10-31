package pro.fontoura.cursomc.services.exceptions;

public class ObjectNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException(Integer id, String classe) {
		super("Objeto não encontrado! Id: " + id + ", Tipo: " + classe);
	}

	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public ObjectNotFoundException(String string) {
		super("Objeto não encontrado! " + string);

	}

}
