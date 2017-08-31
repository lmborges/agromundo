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
	    		" SELECT EM_PR.EMAIL,  EM_PR.ID_FORNECEDOR, EM_PR.NOME_FANSATIA, "
	    				+" SUM(EM_PR.QTD_EMBALAGEM) QTD_EMBALAGEM "
	    				+" , SUM ( CASE WHEN (EM_PR.TIPO_UNIDADE = '1') THEN EM_PR.QTD_MEDIDA  ELSE 0 END) QTD_LISTROS "
	    				+" , SUM ( CASE WHEN (EM_PR.TIPO_UNIDADE = '2') THEN EM_PR.QTD_MEDIDA  ELSE 0 END) QTD_QUILOS "
	    				+" FROM ( "
	    						+" SELECT  FC.DS_EMAIL AS EMAIL,  FC.ID_FORNECEDOR, FC.DS_NOME_FANTASIA NOME_FANSATIA "
	    				+" ,PR.QTD_MEDIDA, TP.CD_UNIDADE_MEDIDA TIPO_UNIDADE, '0' AS QTD_EMBALAGEM "
	    				+" FROM AGROMUNDO.FORNECEDOR FC "
	    				+" INNER JOIN AGROMUNDO.PRODUTO PR ON PR.FK_ID_FORNECEDOR = FC.ID_FORNECEDOR AND PR.DT_VALIDADE < NOW() "
	    				+" LEFT JOIN AGROMUNDO.TIPO_PRODUTO TP ON TP.ID_TIPO_PRODUTO = PR.FK_ID_TIPO_PRODUTO "
	    				+" UNION "
	    				+" SELECT FC.DS_EMAIL AS EMAIL,  FC.ID_FORNECEDOR, FC.DS_NOME_FANTASIA NOME_FANSATIA "
	    				+" ,'0' AS QTD_MEDIDA, '0'  TIPO_UNIDADE, COUNT(EM.ID_EMBALAGEM) AS QTD_EMBALAGEM "
	    				+"  FROM AGROMUNDO.FORNECEDOR FC "
	    				 +" INNER JOIN AGROMUNDO.EMBALAGEM EM ON EM.FK_ID_FORNECEDOR = FC.ID_FORNECEDOR "
	    				+" GROUP BY EMAIL, ID_FORNECEDOR, NOME_FANSATIA ) EM_PR "
	    				+" GROUP BY EMAIL, ID_FORNECEDOR, NOME_FANSATIA ",
	        new AbstractListHandler<NotificaFornecedor>() {

	          @Override
	          protected NotificaFornecedor handleRow(ResultSet rs) throws SQLException {
	            return new NotificaFornecedor(rs.getLong("ID_FORNECEDOR"), rs.getString("NOME_FANSATIA"), rs.getString("EMAIL"), rs.getString("QTD_EMBALAGEM"), rs.getString("QTD_LITROS"), rs.getString("QTD_QUILOS") );//rs.getString(1);
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
