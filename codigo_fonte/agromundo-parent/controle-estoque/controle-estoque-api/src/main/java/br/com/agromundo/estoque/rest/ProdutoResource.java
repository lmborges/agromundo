/**
 * 
 */
package br.com.agromundo.estoque.rest;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import br.com.agromundo.estoque.model.dominio.Produto;
import br.com.agromundo.estoque.model.gerenciador.GerenciadorProduto;
import br.com.agromundo.estoque.service.FornecedoresWS;
import br.com.agromundo.libs.exception.BusinessException;
import br.com.agromundo.libs.util.CodigoStatusHttp;
import br.com.agromundo.libs.util.Rotas;
import br.com.agromundo.libs.vo.ConsultaPorListaId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Leonardo Borges
 *
 */
@Api(Rotas.PRODUTO_DISPOSITIVO_SERVICE)
@Path(Rotas.PRODUTO_DISPOSITIVO_SERVICE_PATH)
@RequestScoped
public class ProdutoResource {

  Logger log = Logger.getLogger(ProdutoResource.class);

  @Inject
  GerenciadorProduto gerenciadorProduto;
  
  @Inject
  FornecedoresWS fornecedorWs;
  
  @POST
  @Produces("application/json")
  @ApiOperation(produces = "application/json,application/xml", consumes = "application/json,application/xml", value = "Cadastra um Produto ", notes = "Função usada para cadastrar um Produto", response = Produto.class)
  @ApiResponses({ @ApiResponse(code = CodigoStatusHttp.OK, message = "Produto salvo"),@ApiResponse(code = CodigoStatusHttp.UNAUTHORIZED, message = "Não autorizado"),
      @ApiResponse(code = CodigoStatusHttp.PRECONDITION_FAILED, message = "Erro de validação de dados, corrija e refaça a operação")})
  public Response cadastrar(Produto entidade) {
    try {
      gerenciadorProduto.cadastrar(entidade);
    } catch (BusinessException e) {
      log.error(e);
      return Response.status(CodigoStatusHttp.PRECONDITION_FAILED).entity(e.toJson()).build();
    } catch (Exception e) {
      log.error(e);
      return Response.status(CodigoStatusHttp.INTERNAL_SERVER_ERRO).entity(entidade).build();
    }

    return Response.status(CodigoStatusHttp.OK).entity(entidade).build();
  }
  
  @PUT
  @Produces("application/json")
  @ApiOperation(produces = "application/json,application/xml", consumes = "application/json,application/xml", value = "Altera um Produto ", notes = "Função usada para alterar um Produto", response = Produto.class)
  @ApiResponses({ @ApiResponse(code = CodigoStatusHttp.OK, message = "Produto alterado"),
    @ApiResponse(code = CodigoStatusHttp.UNAUTHORIZED, message = "Não autorizado"),
      @ApiResponse(code = CodigoStatusHttp.PRECONDITION_FAILED, message = "Erro de validação de dados, corrija e refaça a operação")})
  public Response alterar(Produto entidade) {
    try {
      gerenciadorProduto.alterar(entidade);
    } catch (BusinessException e) {
      log.error(e);
      return Response.status(CodigoStatusHttp.PRECONDITION_FAILED).entity(e.toJson()).build();
    } catch (Exception e) {
      log.error(e);
      return Response.status(CodigoStatusHttp.INTERNAL_SERVER_ERRO).entity(entidade).build();
    }

    return Response.status(CodigoStatusHttp.OK).entity(entidade).build();
  }
  
  @GET
  @Produces("application/json")
  @ApiOperation(produces = "application/json,application/xml", consumes = "application/json,application/xml", value = "Lista todos os produtos", notes = "Função usada para listar todos os produto", response = Produto.class)
  @ApiResponses({ @ApiResponse(code = CodigoStatusHttp.OK, message = "Lista obtida"),@ApiResponse(code = CodigoStatusHttp.UNAUTHORIZED, message = "Não autorizado")})
  public Response listarTodos() {
    List<Produto> listaTodosProdutos = gerenciadorProduto.listarTodos();
    return Response.status(CodigoStatusHttp.OK).entity(listaTodosProdutos).build();
  }
  
  @POST
  @Path("solicitarOrcamentoFornecedor")
  @Produces("application/json")
  @ApiOperation(produces = "application/json,application/xml", consumes = "application/json,application/xml", value = "Obtem orçamento diretamente do fornecedor", notes = "Função usada obter o orçamento de uma lista de produtos diretamento do fornecedor", response = Produto.class)
  @ApiResponses({ @ApiResponse(code = CodigoStatusHttp.OK, message = "Valor do orçamento obtido"),@ApiResponse(code = CodigoStatusHttp.UNAUTHORIZED, message = "Não autorizado")})
  public Response solicitarOrcamentoFornecedor(@ApiParam(value = "Lista de Id do Produto que deseja cotar")  ConsultaPorListaId ids) {
    int valorTotal = fornecedorWs.obterOrcamento(ids);
    return Response.status(CodigoStatusHttp.OK).entity(valorTotal).build();
  }
  
  @POST
  @Path("solicitarCompraFornecedor")
  @Produces("application/json")
  @ApiOperation(produces = "application/json,application/xml", consumes = "application/json,application/xml", value = "Solicita a compra de produtos nos fornecedores", notes = "Função usada para comprar produtos nos fornecedores", response = Produto.class)
  @ApiResponses({ @ApiResponse(code = CodigoStatusHttp.OK, message = "Obtido o valor a ser pago"),@ApiResponse(code = CodigoStatusHttp.UNAUTHORIZED, message = "Não autorizado")})
  public Response solicitarCompraFornecedor(@ApiParam(value = "Lista de Id do Produto que deseja comprar") ConsultaPorListaId ids) {
    int valorTotal = fornecedorWs.solicitaCompra(ids);
    return Response.status(CodigoStatusHttp.OK).entity(valorTotal).build();
  }


  @DELETE
  @ApiOperation(produces = "application/json,application/xml", consumes = "application/json,application/xml", value = "Remove um Produto", notes = "Função usada para remover um Produto", response = Integer.class)
  @Produces("application/json")
  @ApiResponses({ @ApiResponse(code = CodigoStatusHttp.OK, message = "Produto excluído"),
    @ApiResponse(code = CodigoStatusHttp.PRECONDITION_FAILED, message = "Erro de validação de dados, corrija e refaça a operação"),@ApiResponse(code = CodigoStatusHttp.UNAUTHORIZED, message = "Não autorizado"),
    @ApiResponse(code = CodigoStatusHttp.EXCEPTION_420_NAO_ENCONTRADO, message = "Produto não encontrado") })
  public Response remover( @ApiParam(value = "Id do Produto que deseja remover") @QueryParam("Produto") int idProduto) {
    try {
      gerenciadorProduto.excluir(Long.valueOf(idProduto));
    } catch (BusinessException e) {
      log.error(e);
      return Response.status(CodigoStatusHttp.PRECONDITION_FAILED).entity(e.toJson()).build();
    } catch (IllegalArgumentException e) {
      log.error(e);
      return Response.status(CodigoStatusHttp.EXCEPTION_420_NAO_ENCONTRADO).build();
    } catch (Exception e) {
      log.error(e);
      return Response.status(CodigoStatusHttp.INTERNAL_SERVER_ERRO).build();
    }
    return Response.status(CodigoStatusHttp.OK).build();
  }

}
