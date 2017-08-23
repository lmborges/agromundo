package br.com.agromundo.estoque.model.dominio;

import java.io.Serializable;
import javax.persistence.*;


/**
 * 
 * @author borges
 *
 */
@Entity
@Table(name="cliente_especial", schema="agromundo")
@NamedQuery(name="ClienteEspecial.findAll", query="SELECT c FROM ClienteEspecial c")
public class ClienteEspecial implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String senha;
	private String login;
	private String nome;

	public ClienteEspecial() {
	}


	@Id
	@SequenceGenerator(name="CLIENTE_ESPECIAL_ID_GENERATOR", sequenceName="DPS_COLOCAR")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLIENTE_ESPECIAL_ID_GENERATOR")
	@Column(name="id_cliente_especial")
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