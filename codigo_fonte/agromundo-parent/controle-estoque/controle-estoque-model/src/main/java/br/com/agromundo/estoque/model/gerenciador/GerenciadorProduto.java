/**
 * 
 */
package br.com.agromundo.estoque.model.gerenciador;
import java.util.List;

import javax.inject.Inject;

import br.com.agromundo.estoque.model.dominio.Produto;
import br.com.agromundo.estoque.model.repositorio.RepositorioProduto;

/**
 * @author Leonardo Borges
 *
 */
public class GerenciadorProduto {

  @Inject
  RepositorioProduto repositorio;
  /**
   * @param entidade
   */
  public Produto cadastrar(Produto entidade) {
    entidade.setId(null);
    return repositorio.cadastrar(entidade);
    
  }
  
  /**
   * @param entidade
   */
  public Produto alterar(Produto entidade) {
    return repositorio.alterar(entidade);
  }


  /**
   * @param token
   * @param idTipoToken
   */
  public void excluir(Long idProduto) {
    repositorio.excluir(idProduto);
    
  }

  /**
   * @return
   */
  public List<Produto> listarTodos() {
    return repositorio.listar();
  }

}
