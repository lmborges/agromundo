/**
 * 
 */
package br.com.agromundo.controle.descaste.services;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import br.com.agromundo.libs.vo.ConsultaPorListaId;
import br.com.agromundo.libs.vo.FornecedorVO;

/**
 * @author Leonardo Borges
 *
 */
public class FornecedoresWS {
  
  
  public List<FornecedorVO> obterListaFornecedores(List<Long> ids){
    try {
      return obterListaFornecedores(new ConsultaPorListaId(ids));
    } catch (IOException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }
  
  
  /**
   * @param consultaPorListaId
   * @return
   * @throws IOException 
   */
  private List<FornecedorVO> obterListaFornecedores(ConsultaPorListaId consultaPorListaId) throws IOException {
    HttpPost post = new HttpPost(listarFornecedorPorListaIdFornecedor());
    List<FornecedorVO> fornecedor;
    try (CloseableHttpClient client = HttpClients.custom().useSystemProperties().build()) {
      post.addHeader( HttpHeaders.CONTENT_TYPE, "application/json");
      Gson gson = new Gson();
      post.setEntity(new StringEntity(gson.toJson(consultaPorListaId)));
      try (CloseableHttpResponse response = client.execute(post)) {
        // Reader reader = new InputStreamReader(response.getEntity().getContent());
        Type listType = new TypeToken<ArrayList<FornecedorVO>>() {
        }.getType();
        fornecedor = gson
            .fromJson(new InputStreamReader(response.getEntity().getContent()), listType);
      }
    }
    return fornecedor;
  }


  public FornecedorVO obterFornecedor(Long id) {
    
    try {
      return obter(id);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
  
  
  
  
  private FornecedorVO obter(Long id) throws IOException {
    HttpGet get = new HttpGet(obterFornecedorId(id));
    FornecedorVO fornecedor;
    try (CloseableHttpClient client = HttpClients.custom().useSystemProperties().build()) {
      try (CloseableHttpResponse response = client.execute(get)) {
        Gson gson = new Gson();
        fornecedor = gson
            .fromJson(new InputStreamReader(response.getEntity().getContent()), FornecedorVO.class);
      }
    }
    
    return fornecedor;
  }

  /**
   * @param id
   * @return
   */
  private String obterFornecedorId(Long id) {
    return listarFornecedor()+"/"+id;
  }
  private String listarFornecedorPorListaIdFornecedor() {
    return listarFornecedor()+"/listarPorId";
  }
  private static String listarFornecedor() {
    return "http://localhost:8080/fornecedores/rest/fornecedor";
  }

}
