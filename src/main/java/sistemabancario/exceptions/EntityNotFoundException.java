 package sistemabancario.exceptions;

 
 
public class EntityNotFoundException extends BancoException{

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String message) {
		super(message);
	}

}
