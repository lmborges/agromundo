/**
 * 
 */
package br.com.agromundo.estoque.model.repositorio;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.agromundo.estoque.model.util.IEntity;

/**
 * @author Leonardo Borges
 *
 */
public class RepositorioGenerico {
  @Inject
  EntityManager em;
  
  <T> T findOneAtivoByProperty(Class<T> classe, String propertyName, Object propertyValue){
    try {
      String query = String.format(" FROM %s " +
                     " WHERE %s = :propertyValue " +
                     " AND ativo = :ativo ", classe.getSimpleName(), propertyName);
      Query typedQuery = em.createNativeQuery(query, classe);
      typedQuery.setParameter("propertyValue", propertyValue);
      return (T) typedQuery.getSingleResult();
    } catch (NoResultException ex) {
      return null;
    }
  }
  
  <T> T findOneByProperty(Class<T> classe, String propertyName, Object propertyValue){
    try {
      String query = String.format(" FROM %s WHERE %s = :propertyValue ", classe.getSimpleName(), propertyName);
      Query typedQuery = em.createNativeQuery(query, classe);
      typedQuery.setParameter("propertyValue", propertyValue);
      return (T) typedQuery.getSingleResult();
    } catch (NoResultException ex) {
      return null;
    }
  }
  
  <T> List<T> findByProperty(Class<T> classe, String propertyName, Object propertyValue){
        String query = String.format(" FROM %s WHERE %s = :propertyValue ", classe.getSimpleName(), propertyName);
        Query typedQuery = em.createNativeQuery(query, classe);
        typedQuery.setParameter("propertyValue", propertyValue);
        return typedQuery.getResultList();
    }
  
   <T> Long countByProperty(Class<T> classe, String propertyName, Object propertyValue){
     String query = String.format(" select COUNT(ent) FROM %s ent WHERE %s = :propertyValue ", classe.getSimpleName(), propertyName);
     Query typedQuery = em.createNativeQuery(query, Long.class);
     typedQuery.setParameter("propertyValue", propertyValue);
     return (Long) typedQuery.getSingleResult();
 }
  
  <T> List<T> findAll(Class<T> classe){
    Query query = em.createNativeQuery("FROM "+classe.getSimpleName(), classe);
    return query.getResultList();
  }
  
  <T> List<T> findAllAtivo(Class<T> classe){
     Query query = em.createNativeQuery("FROM "+classe.getSimpleName()+" WHERE ativo = :ativo", classe);
    return query.getResultList();
  }
  
  /**
   * Salva ou altualiza uma entidade na camada de persitência
   */
  void save(IEntity entity, String usuarioUltimaAlteracao) {
    save(entity);
  }
  
  private void save(IEntity entity){

    if (entity.getId() == null) {
      em.persist(entity);
    } else {
      em.merge(entity);
    }
  }
}
