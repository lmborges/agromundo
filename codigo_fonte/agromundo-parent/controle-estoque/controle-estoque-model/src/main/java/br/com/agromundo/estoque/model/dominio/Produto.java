package br.com.agromundo.estoque.model.dominio;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * 
 * @author borges
 *
 */
@Entity
@Table(name="Produto", schema="agromundo")
@NamedQuery(name="Produto.findAll", query="SELECT p FROM Produto p")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String nome;
	private Date validade;
	private double medida;
	private Fornecedor fornecedor;
	private TipoProduto tipoProduto;

	public Produto() {
	}


	@Id
	@SequenceGenerator(name="PRODUTO_ID_GENERATOR", sequenceName="DPS_COLOCAR")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUTO_ID_GENERATOR")
	@Column(name="id_produto")
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


	@Temporal(TemporalType.DATE)
	@Column(name="dt_validade")
	public Date getValidade() {
		return this.validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}


	@Column(name="qtd_medida")
	public double getMedida() {
		return this.medida;
	}

	public void setMedida(double medida) {
		this.medida = medida;
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


	//bi-directional many-to-one association to TipoProduto
	@ManyToOne
	@JoinColumn(name="fk_id_tipo_produto")
	public TipoProduto getTipoProduto() {
		return this.tipoProduto;
	}

	public void setTipoProduto(TipoProduto tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

}