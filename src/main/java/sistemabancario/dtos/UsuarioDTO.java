package sistemabancario.dtos;

import sistemabancario.entities.Usuario;

public class UsuarioDTO {

	private String nome;
	private String contato;
	private int conta;

	public UsuarioDTO() {
	}

	public UsuarioDTO(String nome, String contato, int conta) {
		this.nome = nome;
		this.contato = contato;
		this.conta = conta;
	}

	public static UsuarioDTO ConverterTo(Usuario usuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setNome(usuario.getNome());
		usuarioDTO.setContato(usuario.getContato());
		usuarioDTO.setConta(usuario.getNumconta());
		return usuarioDTO;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public int getConta() {
		return conta;
	}

	public void setConta(int conta) {
		this.conta = conta;
	}

}
