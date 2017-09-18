package br.com.agromundo.estoque.model.dominio;

/**
 * 
 * @author Leonardo Borges
 *
 */
public class NotificaFornecedor {
  private Long id;
  private Long idExternoFornecedor;
  private String nome;
  private String email;
  private String qtdEmbalagens;
  private String qtdLitros;
  private String qtdQuilos;

  public NotificaFornecedor(Long id, String nome, String email, String qtdEmbalagens,
      String qtdLitros, String qtdQuilos) {
    super();
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.qtdEmbalagens = qtdEmbalagens;
    this.qtdLitros = qtdLitros;
    this.qtdQuilos = qtdQuilos;
  }

  public NotificaFornecedor(Long id, Long idExternoFornecedor, String qtdEmbalagens,
      String qtdLitros, String qtdQuilos) {
    super();
    this.id = id;
    this.idExternoFornecedor = idExternoFornecedor;
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

  /**
   * @return the idExternoFornecedor
   */
  public Long getIdExternoFornecedor() {
    return idExternoFornecedor;
  }

  /**
   * @param idExternoFornecedor
   *          the idExternoFornecedor to set
   */
  public void setIdExternoFornecedor(Long idExternoFornecedor) {
    this.idExternoFornecedor = idExternoFornecedor;
  }

  /**
   * @param nome the nome to set
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * @param email the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }
}
