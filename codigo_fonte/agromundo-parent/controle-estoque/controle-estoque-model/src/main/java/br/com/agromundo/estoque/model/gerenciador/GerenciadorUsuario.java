/**
 * 
 */
package br.com.agromundo.estoque.model.gerenciador;

import javax.inject.Inject;

import org.apache.commons.codec.digest.DigestUtils;

import br.com.agromundo.estoque.model.dominio.Usuario;
import br.com.agromundo.estoque.model.repositorio.RepositorioUsuario;

/**
 * @author Leonardo Borges
 *
 */
public class GerenciadorUsuario {

@Inject
RepositorioUsuario repositorio;

  /**
   * 
   */
  private static final String SALT = "a1@VH%sqb3!@%szk47F";

  public boolean autenticado(String login, String senha) {
    Usuario usuario = repositorio.obterUsuarioPorLogin(login);
    if (usuario == null || usuario.getId() == null) {
      return Boolean.FALSE;
    }
    String salt = obtemSalt(usuario.getId());
    String hashSenha = geraHashSenha(senha, salt);

    if (usuario.getSenha().equals(hashSenha)) {
      return Boolean.TRUE;
    }

    return Boolean.FALSE;
  }

  /**
   * @param senha
   * @param salt
   * @return
   */
  private String geraHashSenha(String senha, String salt) {
    return DigestUtils.sha512Hex(senha + salt);
  }

  /**
   * @param id
   * @return
   */
  private String obtemSalt(Long id) {
    return String.format(SALT, id, id);
  }




  public static void main(String[] args) {
    GerenciadorUsuario ger = new GerenciadorUsuario();
    String obtemSalt = ger.obtemSalt(1L);
    String senhaHash = ger.geraHashSenha("admin", obtemSalt);
    System.out.println("salt: "+obtemSalt);
    System.out.println("hash: "+senhaHash);
    System.out.println(senhaHash);

  }

}
