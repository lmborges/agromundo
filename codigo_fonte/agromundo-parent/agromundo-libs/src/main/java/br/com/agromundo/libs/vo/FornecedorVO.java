/**
 * 
 */
package br.com.agromundo.libs.vo;

/**
 * @author Leonardo Borges
 *
 */
public class FornecedorVO {
  private Long id;
  private String nome;
  private String email;

  /**
   * @param id
   * @param nome
   * @param email
   */
  public FornecedorVO(Long id, String nome, String email) {
    super();
    this.id = id;
    this.nome = nome;
    this.email = email;
  }

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the nome
   */
  public String getNome() {
    return nome;
  }

  /**
   * @param nome
   *          the nome to set
   */
  public void setNome(String nome) {
    this.nome = nome;
  }

  /**
   * @return the email
   */
  public String getEmail() {
    return email;
  }

  /**
   * @param email
   *          the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }
}
