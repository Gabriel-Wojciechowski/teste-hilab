<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html class="h-100">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:choose>
            <c:when  test="${param.action == 'formUpdate'}">
                <title>Editar Usuário</title>
            </c:when>
            <c:otherwise>
                <title>Novo Usuário</title>
            </c:otherwise>
        </c:choose>
        
        <link rel="stylesheet" href="css/cadastro.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">  
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
        <script type="text/javascript" src="jquery.maskedinput-1.1.4.pack.js"/></script>
    </head>
    <body class="text-center">  
        <main class="flex-shrink-0">
            <div class="container my-4">
                <div class="input-group mx-3 d-inline-flex w-auto">
                    <form method="post" action="${param.action == 'formUpdate'? 'UsuarioServlet?action=update':'UsuarioServlet?action=new'}" class="form-signin">
                        <c:choose>
                            <c:when  test="${param.action == 'formUpdate'}">
                                <h2>Editar Usuário</h2>
                            </c:when>
                            <c:otherwise>
                                <h2>Novo Usuário</h2>
                            </c:otherwise>
                        </c:choose>
                        <br>
                        <c:if test="${msg != null}">
                            <span class="alert alert-danger d-sm-inline-flex mb-3" role="alert">
                                ${msg}
                            </span>
                        </c:if>
                        
                        <input type="hidden" class="form-control" id="id" name="id" value="<c:out value="${usuario.id}"/>" required>
                        
                        <div class="form-floating">
                            <input type="text" class="form-control" id="nome" placeholder="Nome" name="nome" value="<c:out value="${usuario.nome}"/>" required>
                            <label for="nome">Nome</label>
                        </div>
                        <br>
                        <div class="form-floating">
                            <input type="date" class="form-control" id="dataNasc" placeholder="Data de Nascimento" name="dataNasc" value="<c:out value="${usuario.dataNasc}"/>" required>
                            <label for="dataNasc">Data de Nascimento</label>
                        </div>
                        <br>
                        <div class="form-floating">
                            <input type="email" class="form-control" id="email" placeholder="Email" name="email" value="<c:out value="${usuario.email}"/>" required>
                            <label for="email">Email</label>
                        </div>
                        <br>
                        <div class="form-floating">
                            <select class="form-select" id="categoria" placeholder="Categoria" name="categoria">
                                <c:forEach var="categoria" items="${categorias}">
                                    <option value="${categoria.id}"
                                            <c:if test="${categoria.id == usuario.categoria.id}">
                                                selected
                                            </c:if>
                                            >${categoria.descricao}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <input type="submit" value="${param.action == 'formUpdate' ? 'Editar' : 'Cadastrar'}" class="w-100 btn btn-lg btn-primary mt-3" />
                        <br>
                        <a class="w-100 btn btn-lg btn-secondary mt-3" href="UsuarioServlet">Voltar</a>
                    </form>
                </div>
            </div>
        </main>
    </body>
</html>