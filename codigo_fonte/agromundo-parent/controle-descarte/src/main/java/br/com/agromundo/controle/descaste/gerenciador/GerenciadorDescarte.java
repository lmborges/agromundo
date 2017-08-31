/**
 * 
 */
package br.com.agromundo.controle.descaste.gerenciador;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;

import br.com.agromundo.controle.descaste.services.EnviarEmail;
import br.com.agromundo.estoque.model.dominio.NotificaFornecedor;
import br.com.agromundo.estoque.model.repositorio.RepositorioFornecedor;

/**
 * @author Leonardo Borges
 *
 */
public class GerenciadorDescarte {

  Logger log = Logger.getLogger(GerenciadorDescarte.class);

  private static final String ASSUNTO_EMAIL = "Agromundo solicita descarte de materias";
  private static final String CORPO_EMAIL_RECOLHIMENTO_FORNECEDOR = "Prezado %s, \n temos em nosso estoque os seguintes quantitativos e aguardamos "
      + "o tão breve recolhimento:" + "\n - %s embalagem(ns);"
      + "\n - %s litro(s) de produto(s) vencido(s);" + "\n - %s quilo(s) de produto(s) vencido(s);"
      + "\n\n Atenciosamente,\n Agromundo";

  @Inject
  RepositorioFornecedor repositorioFornecedor;
  @Inject
  EnviarEmail email;

  public int notificalFornecedoresComPendencia() {
    List<NotificaFornecedor> listaFornecedoresNotificacao;
    int fornecedoresNotificados = 0;
    try {
      listaFornecedoresNotificacao = repositorioFornecedor
          .listarFornecedoresQueDevemSerNotificados();

      for (NotificaFornecedor fornecedor : listaFornecedoresNotificacao) {
        try {
          email.enviarEmail(fornecedor.getNome(), fornecedor.getEmail(),
              String.format(CORPO_EMAIL_RECOLHIMENTO_FORNECEDOR, fornecedor.getNome(),
                  fornecedor.getQtdEmbalagens(), fornecedor.getQtdLitros(),
                  fornecedor.getQtdQuilos()),
              ASSUNTO_EMAIL);
          fornecedoresNotificados++;
        } catch (EmailException e) {
          log.error(e);
        }
      }
    } catch (SQLException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }

    return fornecedoresNotificados;
  }

}
