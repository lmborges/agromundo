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
@Table(name="Fornecedor", schema="agromundo")
@NamedQuery(name="Fornecedor.findAll", query="SELECT f FROM Fornecedor f")
public class Fornecedor implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String cnpj;
	private String email;
	private String nomeFantasia;
	private String razaoSocial;
	private String tipoFornecedor;
	private List<Embalagem> embalagems;
	private List<Produto> produtos;

	public Fornecedor() {
	}


	@Id
	@SequenceGenerator(name="FORNECEDOR_ID_GENERATOR", sequenceName="DPS_COLOCAR")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FORNECEDOR_ID_GENERATOR")
	@Column(name="id_fornecedor")
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	@Column(name="cd_cnpj")
	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	@Column(name="ds_email")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Column(name="ds_nome_fantasia")
	public String getNomeFantasia() {
		return this.nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}


	@Column(name="ds_razao_social")
	public String getRazaoSocial() {
		return this.razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}


	@Column(name="tp_fornecedor")
	public String getTipoFornecedor() {
		return this.tipoFornecedor;
	}

	public void setTipoFornecedor(String tipoFornecedor) {
		this.tipoFornecedor = tipoFornecedor;
	}


	//bi-directional many-to-one association to Embalagem
	@OneToMany(mappedBy="fornecedor")
	public List<Embalagem> getEmbalagems() {
		return this.embalagems;
	}

	public void setEmbalagems(List<Embalagem> embalagems) {
		this.embalagems = embalagems;
	}

	public Embalagem addEmbalagem(Embalagem embalagem) {
		getEmbalagems().add(embalagem);
		embalagem.setFornecedor(this);

		return embalagem;
	}

	public Embalagem removeEmbalagem(Embalagem embalagem) {
		getEmbalagems().remove(embalagem);
		embalagem.setFornecedor(null);

		return embalagem;
	}


	//bi-directional many-to-one association to Produto
	@OneToMany(mappedBy="fornecedor")
	public List<Produto> getProdutos() {
		return this.produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public Produto addProduto(Produto produto) {
		getProdutos().add(produto);
		produto.setFornecedor(this);

		return produto;
	}

	public Produto removeProduto(Produto produto) {
		getProdutos().remove(produto);
		produto.setFornecedor(null);

		return produto;
	}

}