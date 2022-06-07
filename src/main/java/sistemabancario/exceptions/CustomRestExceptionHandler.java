package sistemabancario.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({BancoException.class})
	public ResponseEntity<ApiErrorDTO> handleBancoException(BancoException ex, WebRequest request){
		
		String error = "Erro Interno no sistema";
		ApiErrorDTO apiError = new ApiErrorDTO(ex.getMessage(),error , HttpStatus.INTERNAL_SERVER_ERROR);
		
		return new  ResponseEntity<ApiErrorDTO>(apiError, apiError.getStatus());
	}
	

		
		@ExceptionHandler({EntityNotFoundException.class})
		public ResponseEntity<ApiErrorDTO> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request){
			
			String error = "Recurso não encontrado";
			ApiErrorDTO apiError = new ApiErrorDTO(ex.getMessage(),error , HttpStatus.NOT_FOUND);
			
			return new  ResponseEntity<ApiErrorDTO>(apiError, apiError.getStatus());
		}
        
		@ExceptionHandler(value={DadosInconsistentesException.class})
	    public final ResponseEntity<ApiErrorDTO> handleDadosInconsistentesException(DadosInconsistentesException ex, WebRequest request) {
			String error = "Verifique os dados inseridos";
			ApiErrorDTO apiError = new ApiErrorDTO(ex.getMessage(),error , HttpStatus.BAD_REQUEST);
			
	        return new ResponseEntity<ApiErrorDTO>(apiError, apiError.getStatus());
	    }
}
