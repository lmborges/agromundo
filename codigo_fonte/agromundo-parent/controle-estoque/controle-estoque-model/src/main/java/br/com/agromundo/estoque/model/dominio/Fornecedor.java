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
@Table(name = "Fornecedor", schema = "agromundo")
@NamedQuery(name = "Fornecedor.findAll", query = "SELECT f FROM Fornecedor f")
public class Fornecedor implements IEntity {
  private static final long serialVersionUID = 1L;
  private Long id;
  private Long idExterno;
  private String tipoFornecedor;
  @JsonIgnore
  private List<Embalagem> embalagems;
  @JsonIgnore
  private List<Produto> produtos;

  public Fornecedor() {
  }

  @Id
  @SequenceGenerator(name = "FORNECEDOR_ID_GENERATOR", sequenceName = "agromundo.seq_Fornecedor", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "FORNECEDOR_ID_GENERATOR")
  @Column(name = "id_fornecedor")
  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the idExterno
   */
  @Column(name = "cd_id_externo_fornecedor")
  public Long getIdExterno() {
    return idExterno;
  }

  /**
   * @param idExterno
   *          the idExterno to set
   */
  public void setIdExterno(Long idExterno) {
    this.idExterno = idExterno;
  }

  @Column(name = "tp_fornecedor")
  public String getTipoFornecedor() {
    return this.tipoFornecedor;
  }

  public void setTipoFornecedor(String tipoFornecedor) {
    this.tipoFornecedor = tipoFornecedor;
  }

  // bi-directional many-to-one association to Embalagem
  @OneToMany(mappedBy = "fornecedor")
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

  // bi-directional many-to-one association to Produto
  @OneToMany(mappedBy = "fornecedor")
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
