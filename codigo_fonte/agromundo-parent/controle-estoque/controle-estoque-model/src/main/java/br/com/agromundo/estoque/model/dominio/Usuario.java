package br.com.agromundo.estoque.model.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.agromundo.estoque.model.util.IEntity;


/**
 * 
 * @author Leonardo Borges
 *
 */
@Entity
@Table(name = "usuario", schema = "agromundo")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements IEntity {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String senha;
	private String tipoUsuario;
	private String login;
	private String nome;

	public Usuario() {
	}


  @Id
  @SequenceGenerator(name = "usuario_ID_GENERATOR", sequenceName = "agromundo.seq_usuario", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "usuario_ID_GENERATOR")
	@Column(name="id_usuario")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Column(name="cd_senha")
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	@Column(name="cd_tipo_usuario")
	public String getTipoUsuario() {
		return this.tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}


	@Column(name="ds_login")
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}


	@Column(name="ds_nome")
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}