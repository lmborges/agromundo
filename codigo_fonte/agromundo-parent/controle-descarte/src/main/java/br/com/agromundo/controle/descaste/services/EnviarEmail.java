/**
 * 
 */
package br.com.agromundo.controle.descaste.services;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;

/**
 * @author Leonardo Borges
 *
 */
public class EnviarEmail {

	Logger log = Logger.getLogger(EnviarEmail.class);
	private static final int HOST_PORTA_GMAIL = 465;
	private static final String HOST_NAME_GMAIL = "smtp.gmail.com";
	private static final String NOME_REMETENTE_EMAIL_SISTEMA = "AgroMundo, Agro é pop!";
	private static final String EMAIL_SISTEMA = "agromundo.sistema@gmail.com";

	public void enviarEmail(String nome,String destino, String body, String assunto) throws EmailException{
		   SimpleEmail email = new SimpleEmail();
		   log.debug("alterando hostname...");
		   email.setHostName(HOST_NAME_GMAIL);
		   email.setSmtpPort(HOST_PORTA_GMAIL);
		   //Adicione os destinatários
		   email.addTo(destino, nome);
		   //Configure o seu email do qual enviará
		   email.setFrom(EMAIL_SISTEMA, NOME_REMETENTE_EMAIL_SISTEMA);
		   //Adicione um assunto
		   email.setSubject(assunto);
		   //Adicione a mensagem do email
		   email.setMsg(body);
		   //Para autenticar no servidor é necessário chamar os dois métodos abaixo
		   log.debug("autenticando...");
		   email.setSSLOnConnect(true);
		   email.setAuthentication(EMAIL_SISTEMA, obterSenhaAutenticacao());
		   log.debug("enviando...");
		   email.send();
		   log.debug("Email enviado!");		
	}

	private String obterSenhaAutenticacao() {
		String senhaUsuarioEmail = System.getProperty("senhaEmailSistema");
		if (senhaUsuarioEmail == null || senhaUsuarioEmail.trim().isEmpty()) {
			throw new IllegalArgumentException("É necessário informar o parametro -DsenhaEmailSistema com a senha do email: "+EMAIL_SISTEMA);
		}
		return senhaUsuarioEmail;
	}
}
