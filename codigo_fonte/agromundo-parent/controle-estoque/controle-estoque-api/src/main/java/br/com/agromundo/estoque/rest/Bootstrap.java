package br.com.agromundo.estoque.rest;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.google.common.base.Throwables;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.models.Swagger;
import io.swagger.models.auth.OAuth2Definition;

@WebServlet(loadOnStartup = 2)
public class Bootstrap extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		try {
			String arquivoConfiguracao = config.getServletContext().getInitParameter("arquivoConfiguracao");
			Properties configuracoes = new Properties();
			configuracoes.load(new FileInputStream(System.getProperty("arquivos") + "/" + arquivoConfiguracao));

			BeanConfig beanConfig = new BeanConfig();
			beanConfig.setVersion(readVersion(config.getServletContext()));
			beanConfig.setBasePath("/controle-estoque-web/rest");
			beanConfig.setResourcePackage("br.com.mundoagro.estoque.rest");
			beanConfig.setScan(true);

			ServletContext context = config.getServletContext();
			Swagger swagger = new Swagger().info(beanConfig.getInfo());
			swagger.securityDefinition("capes_oauth",
					new OAuth2Definition().implicit(configuracoes.getProperty("idp.oauth.url")));
			context.setAttribute("swagger", swagger);

		} catch (Exception e) {
			Throwables.propagate(e);
		}
	}

	public String readVersion(ServletContext sc) {
		try {
			InputStream inputStream = sc.getResourceAsStream("/META-INF/MANIFEST.MF");
			Manifest manifest = new Manifest(inputStream);
			Attributes attributes = manifest.getMainAttributes();
			return attributes.getValue("Specification-Version");
		} catch (Exception e) {
			throw new RuntimeException("Ocorreu um erro ao recuperar numero de versão", e);
		}
	}
}