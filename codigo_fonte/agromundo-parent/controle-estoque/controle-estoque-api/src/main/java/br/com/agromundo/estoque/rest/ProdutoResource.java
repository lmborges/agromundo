/**
 * 
 */
package br.com.agromundo.estoque.rest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.agromundo.libs.exception.BusinessException;
import org.agromundo.libs.util.CodigoStatusHttp;
import org.agromundo.libs.util.Rotas;
import org.apache.log4j.Logger;

import br.com.agromundo.estoque.model.dominio.Produto;
import br.com.agromundo.estoque.model.gerenciador.GerenciadorProduto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Leonardo Borges
 *
 */
@Api(Rotas.TOKEN_DISPOSITIVO_SERVICE)
@Path(Rotas.TOKEN_DISPOSITIVO_SERVICE_PATH)
@RequestScoped
public class ProdutoResource {

  Logger log = Logger.getLogger(ProdutoResource.class);

  @Inject
  GerenciadorProduto gerenciadorProduto;
  
  @POST
  @ApiOperation(produces = "application/json,application/xml", consumes = "application/json,application/xml", value = "Cadastra um token de dispositivo", notes = "Função usada para cadastrar um token de dispositivo", response = Produto.class)
  @ApiResponses({ @ApiResponse(code = CodigoStatusHttp.OK, message = "Token dispositivo salvo"),
      @ApiResponse(code = CodigoStatusHttp.PRECONDITION_FAILED, message = "Erro de validação de dados, corrija e refaça a operação"),
      @ApiResponse(code = CodigoStatusHttp.EXCEPTION_210_JA_CADASTRADO, message = "Token já estava cadastrado") })
  public Response cadastrar(Produto entidade) {
    try {
      gerenciadorProduto.cadastrar(entidade);
    } catch (BusinessException e) {
      return Response.status(CodigoStatusHttp.PRECONDITION_FAILED).entity(e.toJson()).build();
    } catch (IllegalArgumentException e) {
      return Response.status(CodigoStatusHttp.EXCEPTION_210_JA_CADASTRADO).entity(entidade).build();
    } catch (Exception e) {
      log.error(e);
      return Response.status(CodigoStatusHttp.INTERNAL_SERVER_ERRO).entity(entidade).build();
    }

    return Response.status(CodigoStatusHttp.OK).entity(entidade).build();
  }

  @DELETE
  @ApiOperation(produces = "application/json,application/xml", consumes = "application/json,application/xml", value = "Remove um token de dispositivo", notes = "Função usada para remover um token de dispositivo", response = Integer.class)
  @ApiResponses({ @ApiResponse(code = CodigoStatusHttp.OK, message = "Token excluído"),
    @ApiResponse(code = CodigoStatusHttp.PRECONDITION_FAILED, message = "Erro de validação de dados, corrija e refaça a operação"),
    @ApiResponse(code = CodigoStatusHttp.EXCEPTION_420_NAO_ENCONTRADO, message = "Token não encontrado") })
  public Response remover( @ApiParam(value = "Código do token que deseja remover") @FormParam("token") String token,
      @ApiParam(value = "1 para Firebase") @FormParam("idTipoToken") int idTipoToken) {
    try {
      gerenciadorProduto.excluir(token, idTipoToken);
    } catch (BusinessException e) {
      return Response.status(CodigoStatusHttp.PRECONDITION_FAILED).entity(e.toJson()).build();
    } catch (IllegalArgumentException e) {
      return Response.status(CodigoStatusHttp.EXCEPTION_420_NAO_ENCONTRADO).build();
    } catch (Exception e) {
      log.error(e);
      return Response.status(CodigoStatusHttp.INTERNAL_SERVER_ERRO).build();
    }
    return Response.status(CodigoStatusHttp.OK).build();
  }

}
