/**
 * 
 */
package br.com.agromundo.libs.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author Leonardo Borges
 *
 */
public class ConsultaPorListaId implements Serializable{

  List<Long> ids;
  
  public ConsultaPorListaId() {
    super();
  }
  

  /**
   * @param ids
   */
  public ConsultaPorListaId(List<Long> ids) {
    super();
    this.ids = ids;
  }

  /**
   * @return the ids
   */
  public List<Long> getIds() {
    return ids;
  }

}
