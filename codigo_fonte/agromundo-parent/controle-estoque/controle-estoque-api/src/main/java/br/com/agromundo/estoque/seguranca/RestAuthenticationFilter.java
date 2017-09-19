/**
 * 
 */
package br.com.agromundo.estoque.seguranca;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

/**
 * @author Leonardo Borges
 *
 */
public class RestAuthenticationFilter implements javax.servlet.Filter {
  public static final String AUTHENTICATION_HEADER = "Authorization";

  private List<Pattern> exclusions = new LinkedList<Pattern>();

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter)
      throws IOException, ServletException {
    if (request instanceof HttpServletRequest) {
      boolean doFilter = true;
      HttpServletRequest req = (HttpServletRequest) request;
      String requestedURI = req.getQueryString() != null
          ? String.format("%s?%s", req.getRequestURI(), req.getQueryString()) : req.getRequestURI();
      for (Pattern exclusion : exclusions) {
        if (exclusion
            .matcher(
                requestedURI.substring(((HttpServletRequest) request).getContextPath().length()))
            .matches()) {
          doFilter = false;
          break;
        }
      }

      if (!doFilter) {
        filter.doFilter(request, response);
      }else {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authCredentials = httpServletRequest.getHeader(AUTHENTICATION_HEADER);

        // better injected
        AuthenticationService authenticationService = new AuthenticationService();

        boolean authenticationStatus = authenticationService.authenticate(authCredentials);

        if (authenticationStatus) {
          filter.doFilter(request, response);
        } else {
          if (response instanceof HttpServletResponse) {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
          }
        }
      }
    }
  }

  @Override
  public void destroy() {
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    String excludePaths = filterConfig.getInitParameter("excludePaths");

    if (!StringUtils.isBlank(excludePaths)) {
      for (String exclusion : excludePaths.split(" ")) {
        exclusions.add(Pattern.compile(exclusion));
      }
    }
  }

}
