package br.com.agromundo.estoque.model.dominio;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * 
 * @author borges
 *
 */
@Entity
@Table(name="tipo_produto", schema="agromundo")
@NamedQuery(name="TipoProduto.findAll", query="SELECT t FROM TipoProduto t")
public class TipoProduto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private double unidadeMedida;
	private String nome;
	private List<Produto> produtos;

	public TipoProduto() {
	}


	@Id
	@SequenceGenerator(name="TIPO_PRODUTO_ID_GENERATOR", sequenceName="DPS_COLOCAR")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TIPO_PRODUTO_ID_GENERATOR")
	@Column(name="id_tipo_produto")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Column(name="cd_unidade_medida")
	public double getUnidadeMedida() {
		return this.unidadeMedida;
	}

	public void setUnidadeMedida(double unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}


	@Column(name="ds_nome")
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	//bi-directional many-to-one association to Produto
	@OneToMany(mappedBy="tipoProduto")
	public List<Produto> getProdutos() {
		return this.produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto addProduto(Produto produto) {
		getProdutos().add(produto);
		produto.setTipoProduto(this);

		return produto;
	}

	public Produto removeProduto(Produto produto) {
		getProdutos().remove(produto);
		produto.setTipoProduto(null);

		return produto;
	}

}