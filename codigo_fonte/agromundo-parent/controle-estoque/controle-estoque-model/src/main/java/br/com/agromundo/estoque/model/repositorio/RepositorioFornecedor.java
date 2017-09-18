/**
 * 
 */
package br.com.agromundo.estoque.model.repositorio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.AbstractListHandler;

import br.com.agromundo.estoque.model.dominio.NotificaFornecedor;
import br.com.agromundo.estoque.model.util.AgroMundo;

/**
 * @author Leonardo Borges
 *
 */
public class RepositorioFornecedor {

	
	  @Inject
	  @AgroMundo
	  private DataSource agroMundo;

	/**
	 * @return
	 * @throws SQLException 
	 */
	public List<NotificaFornecedor> listarFornecedoresQueDevemSerNotificados() throws SQLException {
		
		QueryRunner query = new QueryRunner(agroMundo);
		
	    List<NotificaFornecedor> retorno = query.query(
	        " SELECT EM_PR.ID_EXTERNO_FORNECEDOR,  EM_PR.ID_FORNECEDOR, "+
	            "              SUM(EM_PR.QTD_EMBALAGEM) QTD_EMBALAGEM  "+
	            "              , SUM ( CASE WHEN (EM_PR.TIPO_UNIDADE = '1') THEN EM_PR.QTD_MEDIDA  ELSE 0 END) QTD_LITROS  "+
	            "              , SUM ( CASE WHEN (EM_PR.TIPO_UNIDADE = '2') THEN EM_PR.QTD_MEDIDA  ELSE 0 END) QTD_QUILOS  "+
	            "              FROM (  "+
	            "                  SELECT  FC.CD_ID_EXTERNO_FORNECEDOR  AS ID_EXTERNO_FORNECEDOR,  FC.ID_FORNECEDOR "+
	            "              ,PR.QTD_MEDIDA, TP.CD_UNIDADE_MEDIDA TIPO_UNIDADE, '0' AS QTD_EMBALAGEM  "+
	            "              FROM AGROMUNDO.FORNECEDOR FC  "+
	            "              INNER JOIN AGROMUNDO.PRODUTO PR ON PR.FK_ID_FORNECEDOR = FC.ID_FORNECEDOR AND PR.DT_VALIDADE < NOW()  "+
	            "              LEFT JOIN AGROMUNDO.TIPO_PRODUTO TP ON TP.ID_TIPO_PRODUTO = PR.FK_ID_TIPO_PRODUTO  "+
	            "              UNION  "+
	            "              SELECT FC.CD_ID_EXTERNO_FORNECEDOR AS ID_EXTERNO_FORNECEDOR,  FC.ID_FORNECEDOR "+
	            "              ,'0' AS QTD_MEDIDA, '0'  TIPO_UNIDADE, COUNT(EM.ID_EMBALAGEM) AS QTD_EMBALAGEM  "+
	            "               FROM AGROMUNDO.FORNECEDOR FC  "+
	            "               INNER JOIN AGROMUNDO.EMBALAGEM EM ON EM.FK_ID_FORNECEDOR = FC.ID_FORNECEDOR  "+
	            "              GROUP BY ID_EXTERNO_FORNECEDOR, ID_FORNECEDOR ) EM_PR  "+
	            "              GROUP BY ID_EXTERNO_FORNECEDOR, ID_FORNECEDOR  ",
	        new AbstractListHandler<NotificaFornecedor>() {

	          @Override
	          protected NotificaFornecedor handleRow(ResultSet rs) throws SQLException {
	            return new NotificaFornecedor(rs.getLong("ID_FORNECEDOR"), rs.getLong("ID_EXTERNO_FORNECEDOR"), rs.getString("QTD_EMBALAGEM"), rs.getString("QTD_LITROS"), rs.getString("QTD_QUILOS") );
	          }
	        });
		
		return retorno;
	}

	/**
	 * @return
	 */
	private List<NotificaFornecedor> retornaMockTemporario() {
		List<NotificaFornecedor> fornecedores = new ArrayList();
		for (int i = 0; i < 5; i++) {
			fornecedores.add(new NotificaFornecedor(1L, "Jaum "+i, "odranoel.df@gmail.com", i+"", i+"", i+""));
		}
		
		return fornecedores;
	}

}
