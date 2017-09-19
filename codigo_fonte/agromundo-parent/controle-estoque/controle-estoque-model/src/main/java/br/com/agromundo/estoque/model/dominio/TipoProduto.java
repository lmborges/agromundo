package br.com.agromundo.estoque.model.dominio;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.agromundo.estoque.model.util.IEntity;

/**
 * 
 * @author Leonardo Borges
 *
 */
@Entity
@Table(name = "tipo_produto", schema = "agromundo")
@NamedQuery(name = "TipoProduto.findAll", query = "SELECT t FROM TipoProduto t")
public class TipoProduto implements IEntity {
  private static final long serialVersionUID = 1L;
  private Long id;
  private double unidadeMedida;
  private String nome;
  @JsonIgnore
  private List<Produto> produtos;

  public TipoProduto() {
  }

  @Id
  @SequenceGenerator(name = "TB_tipo_produto_ID_GENERATOR", sequenceName = "agromundo.seq_tipo_produto",  allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "TB_tipo_produto_ID_GENERATOR")
  @Column(name = "id_tipo_produto")
  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "cd_unidade_medida")
  public double getUnidadeMedida() {
    return this.unidadeMedida;
  }

  public void setUnidadeMedida(double unidadeMedida) {
    this.unidadeMedida = unidadeMedida;
  }

  @Column(name = "ds_nome")
  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  // bi-directional many-to-one association to Produto
  @OneToMany(mappedBy = "tipoProduto")
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
