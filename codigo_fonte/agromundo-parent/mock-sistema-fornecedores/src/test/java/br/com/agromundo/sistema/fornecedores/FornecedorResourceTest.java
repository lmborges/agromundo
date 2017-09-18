/**
 * 
 */
package br.com.agromundo.sistema.fornecedores;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.agromundo.libs.vo.ConsultaPorListaId;
import br.com.agromundo.libs.vo.FornecedorVO;

/**
 * @author Leonardo Borges
 *
 */
public class FornecedorResourceTest {

  @Test
  public void listaTodosOsFornecedores() throws IOException {

    HttpGet get = new HttpGet(listarFornecedor());
    List<FornecedorVO> fornecedor;
    try (CloseableHttpClient client = HttpClients.custom().useSystemProperties().build()) {
      try (CloseableHttpResponse response = client.execute(get)) {

        Gson gson = new Gson();
        // Reader reader = new InputStreamReader(response.getEntity().getContent());
        Type listType = new TypeToken<ArrayList<FornecedorVO>>() {
        }.getType();
        fornecedor = gson
            .fromJson(new InputStreamReader(response.getEntity().getContent()), listType);
      }
    }
    System.out.println(fornecedor.toString());
    Assert.assertFalse(fornecedor == null);
  }
  
  @Test
  public void dadoUmIdFornecedorObtemFornecedor() throws IOException {

    HttpGet get = new HttpGet(obterFornecedorId());
    FornecedorVO fornecedor;
    try (CloseableHttpClient client = HttpClients.custom().useSystemProperties().build()) {
      try (CloseableHttpResponse response = client.execute(get)) {

        Gson gson = new Gson();
        // Reader reader = new InputStreamReader(response.getEntity().getContent());
        fornecedor = gson
            .fromJson(new InputStreamReader(response.getEntity().getContent()), FornecedorVO.class);
      }
    }
    System.out.println(fornecedor.toString());
    Assert.assertFalse(fornecedor == null);
  }
  @Test
  public void dadoUmaListIdListaOsFornecedores() throws IOException {

    HttpPost post = new HttpPost(obterListarFornecedorPorListaId());
    List<FornecedorVO> fornecedor;
    try (CloseableHttpClient client = HttpClients.custom().useSystemProperties().build()) {
      post.addHeader( HttpHeaders.CONTENT_TYPE, "application/json");
      post.setEntity(obterListaIdFornecedor());
      try (CloseableHttpResponse response = client.execute(post)) {
        Gson gson = new Gson();
        // Reader reader = new InputStreamReader(response.getEntity().getContent());
        Type listType = new TypeToken<ArrayList<FornecedorVO>>() {
        }.getType();
        fornecedor = gson
            .fromJson(new InputStreamReader(response.getEntity().getContent()), listType);
      }
    }
    System.out.println(fornecedor.toString());
    Assert.assertFalse(fornecedor == null);
  }
  

  /**
   * @return
   */
  private HttpEntity obterListaIdFornecedor() {
    List<Long> listaId = new ArrayList<Long>();
    listaId.add(3L);
    listaId.add(7L);
    Gson gson = new Gson();
    try {
      return new StringEntity(gson.toJson(new ConsultaPorListaId(listaId)));
    } catch (UnsupportedEncodingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return null;
    }
  }

  private static String obterFornecedorId() {
    return listarFornecedor()+"/1";
  }
  private static String obterListarFornecedorPorListaId() {
    return listarFornecedor()+"/listarPorId";
  }

  private static String listarFornecedor() {
    return "http://localhost:8080/fornecedores/rest/fornecedor";
  }

}
