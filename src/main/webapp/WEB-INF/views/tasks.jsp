<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: psolski
  Date: 25.10.2019
  Time: 09:20
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="pl">
<head>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <meta charset="UTF-8">
    <%@ include file="links.jspf" %>
    <%--    <script src="http://code.jquery.com/jquery-1.9.0rc1.js"></script>--%>

    <%--    <script src="http://code.jquery.com/jquery-migrate-1.0.0rc1.js"></script>--%>
    <script src="js/books.js"></script>
</head>
<body>


<%@ include file="header.jsp" %>


<div class="container">

    <form id="edit-form">
        <div id="tasksListHeader">
            <h2><br><br><br></h2>
            <h2>Dodaj zadanie</h2><br>
        </div>


        <div class="row">
            <div class="form-group col-md-6">
                <label>Autor:</label>
                <input type="text" placeholder="Podaj autora" id="formAuth" class="form-control">
            </div>
            <div class="form-group col-md-6">
                <label>Tytuł:</label>
                <input type="text" placeholder="Podaj tytuł" id="title" class="form-control">
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-6">
                <label>ISBN</label>
                <input type="text" placeholder="Podaj isbn" id="isbn" class="form-control">
            </div>
            <div class="form-group col-md-6">
                <label>Typ</label>
                <input type="text" placeholder="Podaj typ" id="type" class="form-control">
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-6">
                <label>Publisher</label>
                <input type="text" placeholder="Podaj isbn" id="publisher" class="form-control">
            </div>
            <div class="form-group col-md-6">
                <label hidden>Typ</label>
                <input type="text" placeholder="Podaj typ" id="backup" hidden class="form-control">
            </div>
        </div>
        <input type="submit" id="submitBtn" data-method="POST" class="btn btn-success">
        <br><br>
    </form>

    <div>
        <h2><br><br><br></h2>

    </div>

    <div class="card bg-light mb-3">
        <div class="card-header">
            <h2>Lista zadań</h2><br>
        </div>
        <div class="card-body">
            <table class="table table-hover" id="header">
                <thead>
                <tr>
                    <th scope="col">Lp.</th>
                    <th scope="col" class="col-6 text-left">Zadanie</th>
                    <th scope="col">
                        <%--                <button class="btn btn-success" id="addBtn">Dodaj zadanie</button>--%>
                    </th>
                </tr>
                </thead>
                <tbody id="tBody">
                <c:set var="count" value="0" scope="page"/>
                <c:forEach items="${tasks}" var="task">
                    <c:set var="count" value="${count + 1}" scope="page"/>
                    <tr>
                        <td>
                                <%--                    <c:out value="${task.id}"></c:out>--%>
                            <c:out value="${count}"></c:out>
                        </td>
                        <td>
                            <c:out value="${task.description}"></c:out>
                        </td>
                        <td></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

</div>
<h2><br><br></h2>
<%@ include file="footer.jsp" %>
</body>
</html>
