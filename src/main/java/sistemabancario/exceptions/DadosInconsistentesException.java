package sistemabancario.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DadosInconsistentesException extends RuntimeException {

	public DadosInconsistentesException(String message) {
		super(message);
	
	}
	public DadosInconsistentesException(String message,Throwable cause) {
		super(message,cause);
	
	}

	private static final long serialVersionUID = 1L;

	

}
