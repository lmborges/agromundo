/**
 * 
 */
package br.com.agromundo.estoque.model.repositorio;

import java.util.ArrayList;
import java.util.List;

import br.com.agromundo.estoque.model.dominio.NotificaFornecedor;

/**
 * @author Leonardo Borges
 *
 */
public class RepositorioFornecedor {


	/**
	 * SALVAR QUERY
	 * SELECT FC.DS_EMAIL AS EMAIL,  FC.ID_FORNECEDOR, FC.DS_NOME_FANTASIA NOME_FANSATIA, COUNT(EM.ID_EMBALAGEM) AS QTD_EMBALAGENS FROM AGROMUNDO.FORNECEDOR FC 
		LEFT JOIN AGROMUNDO.PRODUTO PR ON PR.FK_ID_FORNECEDOR = FC.ID_FORNECEDOR AND PR.dt_validade < now()
		LEFT JOIN AGROMUNDO.TIPO_PRODUTO TP ON TP.ID_TIPO_PRODUTO = PR.fk_id_tipo_produto
		LEFT JOIN AGROMUNDO.EMBALAGEM EM ON EM.FK_ID_FORNECEDOR = FC.ID_FORNECEDOR
		GROUP BY FC.DS_EMAIL, FC.ID_FORNECEDOR, FC.DS_NOME_FANTASIA
	 */
	/**
	 * @return
	 */
	public List<NotificaFornecedor> listarFornecedoresQueDevemSerNotificados() {
		return retornaMockTemporario();
	}

	/**
	 * @return
	 */
	private List<NotificaFornecedor> retornaMockTemporario() {
		List<NotificaFornecedor> fornecedores = new ArrayList();
		for (int i = 0; i < 5; i++) {
			fornecedores.add(new NotificaFornecedor(1L, "Jaum "+1, "odranoel.df@gmail.com", i+"", i+"", i+""));
		}
		
		return fornecedores;
	}

}
