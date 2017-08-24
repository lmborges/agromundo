package br.com.agromundo.estoque.model.dominio;
/**
 * 
 * @author Leonardo Borges
 *
 */
public class NotificaFornecedor {
	private Long id;
	private String nome;
	private String email;
	private String qtdEmbalagens;
	private String qtdLitros;
	private String qtdQuilos;

	public NotificaFornecedor(Long id, String nome,String email, String qtdEmbalagens, String qtdLitros, String qtdQuilos) {
		super();
		this.id = id;
		this.nome= nome;
		this.email = email;
		this.qtdEmbalagens = qtdEmbalagens;
		this.qtdLitros = qtdLitros;
		this.qtdQuilos = qtdQuilos;
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getQtdEmbalagens() {
		return qtdEmbalagens;
	}

	public String getQtdLitros() {
		return qtdLitros;
	}

	public String getQtdQuilos() {
		return qtdQuilos;
	}
}
