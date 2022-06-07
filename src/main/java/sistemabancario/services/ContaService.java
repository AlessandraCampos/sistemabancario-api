package sistemabancario.services;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import sistemabancario.entities.Conta;
import sistemabancario.entities.Usuario;
import sistemabancario.exceptions.EntityNotFoundException;
import sistemabancario.repositories.ContaRepository;

@Service
public class ContaService {

	private ContaRepository contaRepository;

	public ContaService(ContaRepository contaRepository) {
		this.contaRepository = contaRepository;
	}

	public Conta novaConta(Conta conta) {

		Random numRadom = new Random();

		int numeroConta = conta.setNumconta(numRadom.nextInt(1000));
		
		
		Optional<Conta> numconta = contaRepository.findBynumconta(numeroConta);
		try {
			if (!(numconta == null)) {
				Conta novaConta = new Conta(numeroConta, conta.getAgencia(), conta.getTipoConta());

				return contaRepository.save(novaConta);
			}else {
				throw new Exception("Conta " + numeroConta + " não encontrada");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conta;
	

		

	}

	public Conta findById(Long id) throws EntityNotFoundException {
		Optional<Conta> contaId = contaRepository.findById(id);
		return contaId.orElseThrow(() -> new EntityNotFoundException("Conta " + id + " não encontrada"));

	}

	public Conta findByNumeroConta(int numeroConta) throws EntityNotFoundException {
		Optional<Conta> numconta = contaRepository.findBynumconta(numeroConta);
		return numconta.orElseThrow(() -> new EntityNotFoundException("Conta " + numeroConta + " não encontrada"));

	}
	
	public Boolean buscarNumeroConta(int numeroConta) throws EntityNotFoundException {
		Optional<Conta> usuario = contaRepository.findBynumconta(numeroConta);
		if (usuario.isPresent()) {
			return true;
		}
		return false ;

	}

	public Conta updateConta(Conta conta) {
		return contaRepository.save(conta);

	}

}
