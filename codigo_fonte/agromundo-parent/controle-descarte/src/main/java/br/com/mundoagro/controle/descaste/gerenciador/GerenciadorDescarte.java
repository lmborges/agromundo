/**
 * 
 */
package br.com.mundoagro.controle.descaste.gerenciador;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;

import br.com.agromundo.estoque.model.dominio.NotificaFornecedor;
import br.com.agromundo.estoque.model.repositorio.RepositorioFornecedor;
import br.com.mundoagro.controle.descaste.services.EnviarEmail;

/**
 * @author Leonardo Borges
 *
 */
public class GerenciadorDescarte {

	Logger log = Logger.getLogger(GerenciadorDescarte.class);

	private static final String ASSUNTO_EMAIL = "Agromundo solicita descarte de materias";
	private static final String CORPO_EMAIL_RECOLHIMENTO_FORNECEDOR = "Prezado %s, \n temos em nosso estoque os seguintes quantitativos e aguardamos "
			+ "o tão breve recolhimento:" + "\n - %s embalagem(ns);" + "\n - %s litro(s) de produto(s) vencido(s);"
			+ "\n - %s quilo(s) de produto(s) vencido(s);" + "\n\n Atenciosamente,\n Agromundo";

	@Inject
	RepositorioFornecedor repositorioFornecedor;
	@Inject
	EnviarEmail email;

	public int notificalFornecedoresComPendencia() {
		List<NotificaFornecedor> listaFornecedoresNotificacao = repositorioFornecedor
				.listarFornecedoresQueDevemSerNotificados();
		int fornecedoresNotificados = 0;
		for (NotificaFornecedor fornecedor : listaFornecedoresNotificacao) {
			try {
				email.enviarEmail(fornecedor.getNome(), fornecedor.getEmail(),
						String.format(CORPO_EMAIL_RECOLHIMENTO_FORNECEDOR, fornecedor.getNome(),
								fornecedor.getQtdEmbalagens(), fornecedor.getQtdLitros(), fornecedor.getQtdQuilos()),
						ASSUNTO_EMAIL);
				fornecedoresNotificados++;
			} catch (EmailException e) {
				log.error(e);
			}
		}

		return fornecedoresNotificados;
	}

}
