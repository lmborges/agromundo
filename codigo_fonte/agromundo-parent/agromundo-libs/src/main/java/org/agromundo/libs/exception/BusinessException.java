/**
 * 
 */
package org.agromundo.libs.exception;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Leonardo Borges
 *
 */

public class BusinessException extends RuntimeException {
  /**
   * Parametros utilizados pelo método toJson
   */
  private static final String JSON_PREFIXO_MSG = "{ \"erros\": [";
  private static final String JSON_COUNTEUDO_MSG = "{\"fault\":{\"faultstring\":\"%s\",\"detail\":{\"errorcode\":\"%s\"}}}";
  private static final String JSON_SEPARADOR_ARRAY = ",";
  private static final String JSON_SUFIXO_MSG = "]}";


  private static final long serialVersionUID = 1L;
  private Map<String, Object[]> errors = new HashMap<String, Object[]>();

  private BusinessException() {
    super();
  }

  private BusinessException(final String mensagem) {
    super(mensagem);
    addErrorMessage(mensagem);
  }

  private BusinessException(final String mensagem, final Throwable causa) {
    super(mensagem, causa);
    addErrorMessage(mensagem, causa);
  }

  public static BusinessException getInstance(final String message) {
    return new BusinessException(message);
  }

  public static BusinessException getInstance() {
    return new BusinessException();
  }

  public static BusinessException getInstance(final String mensagem, final Throwable causa) {
    return new BusinessException(mensagem, causa);
  }

  public boolean hasErrorMessages() {
    return !this.errors.isEmpty();
  }

  public boolean addErrorMessage(String errorMessage, Object... args) {
    boolean result = false;
    if ((errorMessage != null) && (!errorMessage.isEmpty())
        && (!this.errors.containsKey(errorMessage))) {
      result = true;
      this.errors.put(errorMessage, args);
    }
    return result;
  }

  public Map<String, Object[]> getErrorMessages() {
    Map<String, Object[]> copy = new HashMap();
    copy.putAll(this.errors);
    return copy;
  }

  /**
   * @return
   */
  public String toJson() {
    StringBuilder retorno = new StringBuilder(JSON_PREFIXO_MSG);
    
    for (Map.Entry<String, Object[]> item : this.errors.entrySet()) {
      retorno.append(String.format(JSON_COUNTEUDO_MSG, item.getKey(), imprimeValor(item.getValue())));
      retorno.append(JSON_SEPARADOR_ARRAY);
    }
    int posicaoDaUltimaVirgula = retorno.lastIndexOf(JSON_SEPARADOR_ARRAY);
    retorno.delete(posicaoDaUltimaVirgula, posicaoDaUltimaVirgula + JSON_SEPARADOR_ARRAY.length());
   
    retorno.append(JSON_SUFIXO_MSG);
    return retorno.toString();
  }

  private String imprimeValor(Object[] objects) {
    if (objects.length == 0) {
      return "";
    }

    if (objects[0] instanceof Throwable) {
      return ((Throwable) objects[0]).getMessage();
    } else {
      return objects[0].toString();
    }

  }

}
