/**
 * 
 */
package br.com.agromundo.estoque.model.util;

import java.io.Serializable;

/**
 * @author Leonardo Borges
 *
 */
public interface IEntity extends Serializable{
  public Long getId();
  
  public void setId(Long id);
}
