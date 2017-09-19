/**
 * 
 */
package br.com.agromundo.estoque.seguranca;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

import javax.inject.Inject;

import br.com.agromundo.estoque.model.gerenciador.GerenciadorUsuario;

/**
 * @author Leonardo Borges
 *
 */
public class AuthenticationService {
  
  @Inject
  GerenciadorUsuario gerenciadorUsuario;
  
  
	public boolean authenticate(String authCredentials) {

		if (null == authCredentials)
			return false;
		final String encodedUserPassword = authCredentials.replaceFirst("Basic"
				+ " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(
					encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(
				usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		boolean authenticationStatus = gerenciadorUsuario.autenticado(username, password);
		
		return authenticationStatus;
	}
}
