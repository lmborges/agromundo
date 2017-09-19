package br.com.agromundo.estoque.model.dominio;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.agromundo.estoque.model.util.IEntity;

/**
 * 
 * @author Leonardo Borges
 *
 */
@Entity
@Table(name = "Produto", schema = "agromundo")
@NamedQuery(name = "Produto.findAll", query = "SELECT p FROM Produto p")
public class Produto implements IEntity {
  private static final long serialVersionUID = 1L;
  private Long id;
  private String nome;
  private Date validade;
  private BigDecimal medida;
  private Fornecedor fornecedor;
  private TipoProduto tipoProduto;
  private Integer toxico;
  private BigDecimal valorCusto;
  private BigDecimal valorVenda;

  public Produto() {
  }

  @Id
  @SequenceGenerator(name = "TB_Produto_ID_GENERATOR", sequenceName = "agromundo.seq_Produto", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "TB_Produto_ID_GENERATOR")
  @Column(name = "id_produto")
  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "ds_nome")
  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  @Temporal(TemporalType.DATE)
  @Column(name = "dt_validade")
  public Date getValidade() {
    return this.validade;
  }

  public void setValidade(Date validade) {
    this.validade = validade;
  }

  @Column(name = "qtd_medida")
  public BigDecimal getMedida() {
    return this.medida;
  }

  public void setMedida(BigDecimal medida) {
    this.medida = medida;
  }

  // bi-directional many-to-one association to Fornecedor
  @ManyToOne
  @JoinColumn(name = "fk_id_fornecedor")
  public Fornecedor getFornecedor() {
    return this.fornecedor;
  }

  public void setFornecedor(Fornecedor fornecedor) {
    this.fornecedor = fornecedor;
  }

  // bi-directional many-to-one association to TipoProduto
  @ManyToOne
  @JoinColumn(name = "fk_id_tipo_produto")
  public TipoProduto getTipoProduto() {
    return this.tipoProduto;
  }

  public void setTipoProduto(TipoProduto tipoProduto) {
    this.tipoProduto = tipoProduto;
  }
  @Column(name="tp_toxico")
  public Integer getToxico() {
    return this.toxico;
  }

  public void setToxico(Integer toxico) {
    this.toxico = toxico;
  }


  @Column(name="vr_valor_custo")
  public BigDecimal getValorCusto() {
    return this.valorCusto;
  }

  public void setValorCusto(BigDecimal valorCusto) {
    this.valorCusto = valorCusto;
  }


  @Column(name="vr_valor_venda")
  public BigDecimal getValorVenda() {
    return this.valorVenda;
  }

  public void setValorVenda(BigDecimal valorVenda) {
    this.valorVenda = valorVenda;
  }

}
