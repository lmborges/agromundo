package br.com.agromundo.estoque.model.dominio;

import java.io.Serializable;
import javax.persistence.*;


/**
 * 
 * @author borges
 *
 */
@Entity
@Table(name="Embalagem", schema="agromundo")
@NamedQuery(name="Embalagem.findAll", query="SELECT e FROM Embalagem e")
public class Embalagem implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private Fornecedor fornecedor;

	public Embalagem() {
	}


	@Id
	@SequenceGenerator(name="EMBALAGEM_ID_GENERATOR", sequenceName="DPS_COLOCAR")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EMBALAGEM_ID_GENERATOR")
	@Column(name="id_embalagem")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Column(name="ds_nome")
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	//bi-directional many-to-one association to Fornecedor
	@ManyToOne
	@JoinColumn(name="fk_id_fornecedor")
	public Fornecedor getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}