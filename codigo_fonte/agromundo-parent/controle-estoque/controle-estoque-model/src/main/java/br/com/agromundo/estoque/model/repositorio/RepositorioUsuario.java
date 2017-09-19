/**
 * 
 */
package br.com.agromundo.estoque.model.repositorio;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.agromundo.estoque.model.dominio.Usuario;
import br.com.agromundo.estoque.model.util.IEntity;

/**
 * @author Leonardo Borges
 *
 */
public class RepositorioUsuario {
  @Inject
  EntityManager em;

  /**
   * @param login
   * @return
   */
  public Usuario obterUsuarioPorLogin(String login) {
    return findyByPropertie(Usuario.class, "login", login);
  }

  /**
   * @param class1
   * @param string
   * @param login
   * @return
   */
  private <ENTITY extends IEntity> ENTITY findyByPropertie(Class<ENTITY> clazz, String property,
      String value) {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<ENTITY> cq = cb.createQuery(clazz);
    Root<ENTITY> root = cq.from(clazz);
    cq.where(cb.equal(root.get(property), value));
    return em.createQuery(cq).getSingleResult();
  }
}
