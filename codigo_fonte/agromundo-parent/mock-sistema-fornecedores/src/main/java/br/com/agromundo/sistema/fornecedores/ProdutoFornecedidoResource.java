/**
 * 
 */
package br.com.agromundo.sistema.fornecedores;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.agromundo.libs.vo.ConsultaPorListaId;

/**
 * @author Leonardo Borges
 *
 */
@Path("produtoFornecido")
@RequestScoped
public class ProdutoFornecedidoResource {
  
  
  
  /**
   * 
   */
  private static final int VALOR_CABALISTICO_UNITARIO = 60;

  @POST
  @Path("/solicitaCompra")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response solicitarCompra(ConsultaPorListaId lista) {
    int quantidade = 0;
    if(lista.getIds()!= null) {
      quantidade = lista.getIds().size();
    }
    return Response.status(200).entity(quantidade * VALOR_CABALISTICO_UNITARIO).build();

  }
  
  @POST
  @Path("/solicitaOrcamento")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response solicitarOrcamento(ConsultaPorListaId lista) {
    return this.solicitarCompra(lista);

  }

}
