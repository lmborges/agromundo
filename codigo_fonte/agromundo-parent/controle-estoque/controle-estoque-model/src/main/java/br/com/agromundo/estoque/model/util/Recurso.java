/**
 * 
 */
package br.com.agromundo.estoque.model.util;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * @author Leonardo Borges
 *
 */
public class Recurso {

	  
	  @Produces
	  @AgroMundo
	  public DataSource produceDataSourceScpbPush() {
	    try {
	      Context context = new InitialContext();
	      return (DataSource) context.lookup("java:jboss/datasources/agro-mundo");
	    } catch (Exception e) {
	      throw new RuntimeException("Não foi possivel produzir o datasource");
	    }
	  }


	  
	  @Produces
	  public Logger produceLog(InjectionPoint injectionPoint) {
	    return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
	  }
}
