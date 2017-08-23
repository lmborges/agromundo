package org.agromundo.libs.util;

// Nao foi possivel criar um Enum, pois as annotations nao permitem:
// (The value for annotation attribute ApiResponse.code must be a constant expression)
public class CodigoStatusHttp {

    // Codigos criados no sistema
  public static final int EXCEPTION_210_JA_CADASTRADO = 210;
  public static final int EXCEPTION_420_NAO_ENCONTRADO = 420;
    
    // Codigos status HTTP
    public static final int OK = 200;
    public static final int UNAUTHORIZED = 401;
    public static final int FORBIDDEN = 403;
    public static final int NOT_FOUND = 404;
    public static final int METHOD_NOT_ALLOWED = 405;
    public static final int PRECONDITION_FAILED = 412;
    public static final int REQUEST_ENTITY_TOO_LARGE = 413;
    public static final int UNSUPPORTED_MEDIA_TYPE = 415;
    public static final int EXPECTATION_FAILED = 417;
    
    public static final int INTERNAL_SERVER_ERRO = 500;
    
}