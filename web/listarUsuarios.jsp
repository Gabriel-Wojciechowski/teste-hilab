<%@page import="java.util.List"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core' %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de usuários</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.16/jquery.mask.min.js"></script>
        <script type="text/javascript" src="jquery.maskedinput-1.1.4.pack.js"/></script>
    </head>
    <body>
        <main class="flex-shrink-0">
            <div class="container my-4">
                <div class="input-group mx-3 d-inline-flex w-auto">
                    <h2 for="usuarios">Usuários</h2>
                    <br><br>
                    <div class="container">
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Nome</th>
                                    <th scope="col">Data de Nascimento</th>
                                    <th scope="col">E-mail</th>
                                    <th scope="col">Categoria Profissional</th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>        
                                <c:forEach var="u" varStatus="i" items="${usuarios}">     
                                    <tr>
                                        <th scope="row"><c:out value="${u.id}"/></th>
                                        <td><c:out value="${u.nome}"/></td>
                                        <fmt:parseDate value="${u.dataNasc}" pattern="yyyy-MM-dd" var="data" type="date"/>
                                        <td><fmt:formatDate value="${data}" pattern="dd/MM/yyyy"></fmt:formatDate></td>
                                        <td><c:out value="${u.email}"/></td>
                                        <td><c:out value="${u.categoria.descricao}"/></td>
                                        <td><a class="btn btn-secondary" href="UsuarioServlet?id=<c:out value="${u.id}"/>&action=formUpdate">Editar</a></td>
                                        <td><a class="btn btn-danger" href="UsuarioServlet?id=<c:out value="${u.id}"/>&action=remove">Remover</a></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <a class="btn btn-secondary" href="UsuarioServlet?action=formNew">Novo Usuário</a>
                </div>
            </div>
        </main>
    </body>
</html>
