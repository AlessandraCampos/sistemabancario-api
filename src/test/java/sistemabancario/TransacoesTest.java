//package sistemabancario;
//
//
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Test;
//
//import org.junit.runner.RunWith;
//
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import sistemabancario.dtos.TransferenciaDto;
//import sistemabancario.entities.Conta;
//import sistemabancario.entities.TipoTransacao;
//import sistemabancario.entities.Transacoes;
//import sistemabancario.entities.Usuario;
//import sistemabancario.repositories.TransacoesRepository;
//import sistemabancario.repositories.UsuarioRepository;
//import sistemabancario.services.ContaService;
//import sistemabancario.services.TipoTransacaoService;
//import sistemabancario.services.TransacoesService;
//import sistemabancario.services.UsuarioService;
//
//@RunWith(MockitoJUnitRunner.class)
//public class TransacoesTest {
//	
//	
//	@Mock
//	private TransacoesService transacoesService;
//	@Mock
//	private ContaService contaService;
//	@Mock
//	private UsuarioService usuarioService;
//	@Mock
//	private Transacoes transacoes;
//	@Mock
//	private Conta conta;
//	@Mock
//	private Conta conta2;
//	@Mock
//	private TipoTransacao tipotransacao;
//	@Mock
//	private TransacoesRepository transacoesRepository;
//	@Mock
//	private TipoTransacaoService tipoTransacaoService;
//	@Mock
//	private UsuarioRepository usuarioRepository;
//	@Mock
//	private TransferenciaDto transfDto;
//	@Mock
//	private Usuario usuario;
//	@Mock
//	private Usuario usuario2;
//	@Before
//	public void Setup() {
//		MockitoAnnotations.initMocks(this);
//		conta = new Conta(1, 1, (long) 1);
//		conta.setId((long) 1);
//		conta.setSaldo(100);
//		conta2 = new Conta(2, 2, (long) 2);
//		conta2.setId((long) 1);
//		conta2.setSaldo(100);
//		tipotransacao = new TipoTransacao((long) 3);
//		transacoesService = new TransacoesService(transacoesRepository, contaService, tipoTransacaoService,
//				usuarioService);
//		usuario = new Usuario("Nome", "44886765881", "contato", conta);
//		usuario.setId((long)1);
//		usuario2 = new Usuario("Nome2", "44886765882", "contato", conta2);
//		usuario.setId((long)2);
//
//	}
//	@Ignore
//	@Test
//	public void deveSomarSaldoAoDepositar() throws Exception {
//
//		transacoes = new Transacoes(conta, tipotransacao, 150f);
//		transacoesService.calculaDeposito(conta, transacoes);
//		contaService.updateConta(conta);
//		assertEquals(conta.getSaldo(), 250f, 0.0001);
//
//	}
//
//	@Ignore
//	@Test
//	public void deveSubtrairdoSaldoAoSacar() throws Exception {
//
//		transacoes = new Transacoes(conta, tipotransacao, 50f);
//		transacoesService.calculaSaque(conta, transacoes);
//		contaService.updateConta(conta);
//		assertEquals(conta.getSaldo(), 50f, 0.0001);
//
//	}
//	
//	@Ignore
//	@Test
//	public void deveSubtrairdoSaldoAoTransferir() throws Exception {
//		transacoes = new Transacoes(conta, tipotransacao, 50f);
//		transfDto = new TransferenciaDto(100, usuario, usuario2);
//		usuarioService.findByCPF(usuario.getCpf());
//		usuarioService.findByCPF(usuario2.getCpf());
//		contaService.findById(usuario.getContaId().getId());
//		usuario.getContaId();
//		transfDto.getValor();
//		transfDto.getUsuarioOrigem().getContaId();
//		transfDto.getUsuarioDestino().getContaId();
//		transacoes = new Transacoes(usuario.getContaId(),new TipoTransacao((long) 3),transfDto.getValor());
//		transacoesService.calculaTransferencia(transfDto);
//		contaService.updateConta(conta);
//		contaService.updateConta(conta2);
//	}
//	
//	@Ignore
//	@Test(expected=Exception.class)
//	public void deveNegarSaqueComSaldoInsuficiente() throws Exception {
//		transacoes = new Transacoes(conta, tipotransacao, 150f);
//		transacoesService.calculaSaque(conta, transacoes);
//		
//	}
//	@Ignore
//	@Test(expected=Exception.class)
//	public void deveRetornarExcecaoAoTransferirParaContaInexistente() throws Exception {
//		Conta conta3 = new Conta();
//		transacoes = new Transacoes(conta3, tipotransacao, 50f);
//		transfDto = new TransferenciaDto(100, usuario, usuario2);
//		transacoesService.calculaTransferencia(transfDto);
//	}
//	@Ignore
//	@Test(expected=Exception.class)
//	public void deveRetornarExcecaoAoTransferirParaUsuarioInexistente() throws Exception {
//		transacoes = new Transacoes(conta, tipotransacao, 50f);
//		Usuario usuario3 = new Usuario();
//		transfDto = new TransferenciaDto(100, usuario, usuario3);
//		transacoesService.calculaTransferencia(transfDto);
//		
//	}
//	
//	
//
//	
//}
