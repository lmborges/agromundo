/**
 * 
 */
package br.com.agromundo.estoque.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Base64;
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
  
  
  public int obterOrcamento(ConsultaPorListaId ids){
    try {
      return obterOrcamentoFornecedor(ids);
    } catch (IOException e) {
      e.printStackTrace();
      return 0;
    }
  }
  
  public int solicitaCompra(ConsultaPorListaId ids){
    try {
      return solicitaCompraFornecedor(ids);
    } catch (IOException e) {
      e.printStackTrace();
      return 0;
    }
  }
  
  
  /**
   * @param consultaPorListaId
   * @return
   * @throws IOException 
   */
  private int obterOrcamentoFornecedor(ConsultaPorListaId consultaPorListaId) throws IOException {
    HttpPost post = new HttpPost(obterOrcamento());
    int  valor = 0;
    try (CloseableHttpClient client = HttpClients.custom().useSystemProperties().build()) {
      post.addHeader("Authorization","Basic "+ (Base64.getEncoder().encodeToString("admin:admin".getBytes("UTF-8"))));
      post.addHeader( HttpHeaders.CONTENT_TYPE, "application/json");
      Gson gson = new Gson();
      post.setEntity(new StringEntity(gson.toJson(consultaPorListaId)));
      try (CloseableHttpResponse response = client.execute(post)) {
        valor = gson
            .fromJson(new InputStreamReader(response.getEntity().getContent()), Integer.class);
      }
    }
    return valor;
  }
  
  /**
   * @param consultaPorListaId
   * @return
   * @throws IOException 
   */
  private int solicitaCompraFornecedor(ConsultaPorListaId consultaPorListaId) throws IOException {
    HttpPost post = new HttpPost(solicitaCompra());
    int  valor = 0;
    try (CloseableHttpClient client = HttpClients.custom().useSystemProperties().build()) {
      post.addHeader("Authorization","Basic "+ (Base64.getEncoder().encodeToString("admin:admin".getBytes("UTF-8"))));
      post.addHeader( HttpHeaders.CONTENT_TYPE, "application/json");
      Gson gson = new Gson();
      post.setEntity(new StringEntity(gson.toJson(consultaPorListaId)));
      try (CloseableHttpResponse response = client.execute(post)) {
        valor = gson
            .fromJson(new InputStreamReader(response.getEntity().getContent()), Integer.class);
      }
    }
    return valor;
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
  private String obterOrcamento() {
    return listarFornecedor()+"/solicitaOrcamento";
  }
  private String solicitaCompra() {
    return listarFornecedor()+"/solicitaCompra";
  }
  private static String listarFornecedor() {
    return "http://localhost:8080/fornecedores/rest/produtoFornecido";
  }

}
