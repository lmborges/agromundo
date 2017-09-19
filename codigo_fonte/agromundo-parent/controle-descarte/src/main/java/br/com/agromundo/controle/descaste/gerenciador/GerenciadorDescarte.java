/**
 * 
 */
package br.com.agromundo.controle.descaste.gerenciador;

import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.mail.EmailException;
import org.apache.log4j.Logger;

import br.com.agromundo.controle.descaste.services.EnviarEmail;
import br.com.agromundo.controle.descaste.services.FornecedoresWS;
import br.com.agromundo.estoque.model.dominio.NotificaFornecedor;
import br.com.agromundo.estoque.model.repositorio.RepositorioFornecedor;
import br.com.agromundo.libs.vo.FornecedorVO;

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
  @Inject
  FornecedoresWS fornecedorWs;
  static int fornecedoresNotificados;

  public int notificalFornecedoresComPendencia() {
    Instant antes = Instant.now();
    List<NotificaFornecedor> listaFornecedoresNotificacao;
    fornecedoresNotificados = 0;
    try {
      listaFornecedoresNotificacao = repositorioFornecedor
          .listarFornecedoresQueDevemSerNotificados();

      List<Long> idFornecedores = listaFornecedoresNotificacao.stream()
          .map(fornecedor -> fornecedor.getIdExternoFornecedor()).collect(Collectors.toList());
      List<FornecedorVO> dadosCadastraisFornecedores = fornecedorWs
          .obterListaFornecedores(idFornecedores);

      listaFornecedoresNotificacao.parallelStream().forEach(fornecedor -> {
        try {
          mergeFornecedor(fornecedor, dadosCadastraisFornecedores);
          if (fornecedor.getEmail() != null && fornecedor.getEmail().length() > 0) {
            email.enviarEmail(fornecedor.getNome(), fornecedor.getEmail(),
                String.format(CORPO_EMAIL_RECOLHIMENTO_FORNECEDOR, fornecedor.getNome(),
                    fornecedor.getQtdEmbalagens(), fornecedor.getQtdLitros(),
                    fornecedor.getQtdQuilos()),
                ASSUNTO_EMAIL);
            fornecedoresNotificados++;
          }
        } catch (EmailException e) {
          log.error(e);
        }
      });
    } catch (SQLException e1) {
      log.error(e1);
    }
    Duration duracao = Duration.between(antes, Instant.now());
    log.info("Foram notificados "+fornecedoresNotificados+" fornecedores\nTempo de processamento: "+ duracao.getSeconds()+ " em segundos e "+duracao.getNano() + " em nanosegundos");
  
    return fornecedoresNotificados;
  }

  /**
   * @param fornecedor
   * @param dadosCadastraisFornecedores
   */
  private void mergeFornecedor(NotificaFornecedor fornecedor,
      List<FornecedorVO> dadosCadastraisFornecedores) {
    dadosCadastraisFornecedores.stream().forEach(itemDadosFornecedor -> {
      if (itemDadosFornecedor.getId().equals(fornecedor.getIdExternoFornecedor())) {
        fornecedor.setEmail(itemDadosFornecedor.getEmail());
        fornecedor.setNome(itemDadosFornecedor.getNome());
      }
    });
  }

}
