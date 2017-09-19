/**
 * 
 */
package br.com.agromundo.estoque.model.gerenciador;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.agromundo.estoque.model.dominio.Usuario;
import br.com.agromundo.estoque.model.util.IEntity;

/**
 * @author Leonardo Borges
 *
 */
public class GerenciadorUsuario {

  @Inject
  EntityManager em;

  /**
   * 
   */
  private static final String SALT = "a1@VH%sqb3!@%szk47F";

  public boolean autenticado(String login, String senha) {
    Usuario usuario = obtemUsuario(login);
    if (usuario == null || usuario.getId() == null) {
      return Boolean.FALSE;
    }
    String salt = obtemSalt(usuario.getId());
    String hashSenha = geraHashSenha(senha, salt);

    if (usuario.getSenha().equals(hashSenha)) {
      return Boolean.TRUE;
    }

    return Boolean.FALSE;
  }

  /**
   * @param senha
   * @param salt
   * @return
   */
  private String geraHashSenha(String senha, String salt) {
    return DigestUtils.sha512Hex(senha + salt);
  }

  /**
   * @param id
   * @return
   */
  private String obtemSalt(Long id) {
    return String.format(SALT, id, id);
  }

  /**
   * @param login
   * @return
   */
  private Usuario obtemUsuario(String login) {
    return findyByPropertie(Usuario.class, "login", login);
  }

  /**
   * @param class1
   * @param string
   * @param login
   * @return
   */
  private <ENTITY extends IEntity>ENTITY findyByPropertie(Class<ENTITY> clazz, String property, String value) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<ENTITY> cq = cb.createQuery(clazz);
    Root<ENTITY> root = cq.from(clazz);
    cq.where(cb.equal(root.get(property), value));
    return em.createQuery(cq).getSingleResult();
  }

  public static void main(String[] args) {
    GerenciadorUsuario ger = new GerenciadorUsuario();
    String obtemSalt = ger.obtemSalt(1L);
    String senhaHash = ger.geraHashSenha("admin", obtemSalt);
    System.out.println("salt: "+obtemSalt);
    System.out.println("hash: "+senhaHash);
    System.out.println(senhaHash);

  }

}
