/**
 * 
 */
package br.com.agromundo.sistema.fornecedores;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.agromundo.libs.vo.ConsultaPorListaId;
import br.com.agromundo.libs.vo.FornecedorVO;

/**
 * @author Leonardo Borges
 *
 */
@Path("fornecedor")
@RequestScoped
public class FornecedorResource {
  
  static List<FornecedorVO> fornecedores = mockFornecedores();

  /**
   * @return
   */
  private static List<FornecedorVO> mockFornecedores() {
    List<FornecedorVO> listaRetorno = new ArrayList<>();

    listaRetorno.add(new FornecedorVO(1L, "Coca-Cola", "odranoel.df@gmail.com"));
    listaRetorno.add(new FornecedorVO(2L, "Flok", "odranoel.df@gmail.com"));
    listaRetorno.add(new FornecedorVO(3L, "Aminofort", "odranoel.df@gmail.com"));
    listaRetorno.add(new FornecedorVO(4L, "DDVP", "odranoel.df@gmail.com"));
    listaRetorno.add(new FornecedorVO(5L, "Straik", "odranoel.df@gmail.com"));
    listaRetorno.add(new FornecedorVO(6L, "Imbativel", "odranoel.df@gmail.com"));
    listaRetorno.add(new FornecedorVO(7L, "Strong", "odranoel.df@gmail.com"));

    return listaRetorno;
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response listar() {
    return Response.status(200).entity(fornecedores).build();
  }
  
  @POST
  @Path("/listarPorId")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response listarPorId(ConsultaPorListaId lista) {
    System.out.println(lista);
    return Response.status(200).entity(fornecedores).build();
  }

  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response obter(@PathParam("id") Long id) {
    return Response.status(200).entity(obterFornecedorPorId(id)).build();
  }

  private FornecedorVO obterFornecedorPorId(Long id) {
    Optional<FornecedorVO> findFirst = fornecedores.stream().filter(ff -> ff.getId().equals(id))
        .findFirst();
    return findFirst.equals(Optional.empty()) ? null : findFirst.get();
  }

}
