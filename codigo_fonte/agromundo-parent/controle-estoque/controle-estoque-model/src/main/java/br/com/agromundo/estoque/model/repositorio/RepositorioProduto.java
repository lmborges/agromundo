/**
 * 
 */
package br.com.agromundo.estoque.model.repositorio;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import br.com.agromundo.estoque.model.dominio.Produto;
import br.com.agromundo.estoque.model.util.IEntity;

/**
 * @author Leonardo Borges
 *
 */
public class RepositorioProduto extends RepositorioGenerico{

  /**
   * @return
   */
  public List<Produto> listar() {
    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
    CriteriaQuery<Produto> criteriaQuery = criteriaBuilder.createQuery(Produto.class);
    Root<Produto> root = criteriaQuery.from(Produto.class);
    criteriaQuery.select(root);
    TypedQuery<Produto> query = em.createQuery(criteriaQuery);
    return query.getResultList();
  }

  /**
   * @param entidade
   * @return
   */
  @Transactional(TxType.REQUIRED)
  public Produto cadastrar(Produto entidade) {
    em.persist(entidade);
    return entidade;
  }
  
  @Transactional(TxType.REQUIRED)
  public Produto alterar(Produto entidade) {
    em.merge(entidade);
    return entidade;
  }

  /**
   * @param idProduto
   */
  @Transactional(TxType.REQUIRED)
  public void excluir(Long idProduto) {
    Produto prod = findyById(Produto.class,idProduto);
    em.remove(prod);
    
  }

  private  <ENTITY extends IEntity> ENTITY findyById(Class<ENTITY> classe, Long idProduto) {
    return em.find(classe, idProduto);
  }
  

}
