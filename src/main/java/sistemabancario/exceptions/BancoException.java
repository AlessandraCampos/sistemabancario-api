 package sistemabancario.exceptions;

public class BancoException extends Exception{

	private static final long serialVersionUID = -842933078036407119L;
	
	private String message;

	public BancoException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
